package com.ythd.ower.b2c.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.dto.ProductConfirmDto;
import com.ythd.ower.b2c.dto.ProductPayDto;
import com.ythd.ower.b2c.mapper.AppProductCommentMapper;
import com.ythd.ower.b2c.mapper.AppProductMapper;
import com.ythd.ower.b2c.mapper.AppProductOrderMapper;
import com.ythd.ower.b2c.mapper.AppProductStockMapper;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.b2c.model.ProductOrderGoodsModel;
import com.ythd.ower.b2c.model.ProductOrderModel;
import com.ythd.ower.b2c.model.ProductStockModel;
import com.ythd.ower.b2c.service.AppProductService;
import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.constants.SpecificSymbolConstants;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.common.tools.TimeUtils;
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.mapper.AppAddressMapper;
import com.ythd.ower.member.model.UserAddressModel;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ythd.ower.task.constant.JobType;
import com.ythd.ower.task.constant.TimerTaskConstant;
import com.ythd.ower.task.job.QuartzManager;
import com.ythd.ower.task.model.TimerTaskModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

/**
 * 商品服务类 提供商品操作基础服务
 * 该服务类只提供基础的功能  列表和详情
 * 具体页面的返回值 在api项目中组装
 */
@Service
public class AppProductServiceImpl implements AppProductService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppProductServiceImpl.class);
  @Resource
  private AppProductStockMapper appProductStockMapper;

  @Resource
  private AppProductMapper appProductMapper;

  @Resource
  private AppAddressMapper appAddressMapper;

  @Resource
  private AppProductCommentMapper appProductCommentMapper;

  @Resource
  private AppProductOrderMapper appProductOrderMapper;

  @Resource
  private QuartzManager quartzManager;


  @Override
  public List<ProductModel> productListByPage(PageData requestParam) {
    List<ProductModel> list = appProductMapper.findProductByPage(Page.builder(requestParam));
    return list;
  }

  @Override
  public List<ProductModel> productList(PageData requestParam) {
    return appProductMapper.findProduct(requestParam);
  }

  @Override
  public ProductModel productDetail(PageData requestParam) {
    return appProductMapper.findProductDetail(requestParam.getAsInteger(ProductConstant.PRODUCT_ID));
  }

  @Override
  public ProductStockModel stockDetail(PageData pageData) {
    String attrVals = pageData.getAsString(ProductConstant.ATTR_VALS);
    Stream.of(attrVals.split(SpecificSymbolConstants.BACKSLASH + SpecificSymbolConstants.VERTICAL_LINE)).forEach(item -> System.out.println(item));
    Optional<String> valsOpt = Stream.of(attrVals.split(SpecificSymbolConstants.BACKSLASH + SpecificSymbolConstants.VERTICAL_LINE)).sorted().reduce((a, b) -> String.join(SpecificSymbolConstants.VERTICAL_LINE, a, b));
    if (!valsOpt.isPresent()) {
      throw new BizServiceException(ErrorCodesContants.PARAM_ERROR);
    }
    LOGGER.info("排序之后的字符串为：{}", valsOpt.get());
    return appProductStockMapper.findByAttrValues(valsOpt.get(), pageData.getAsString(ProductConstant.PRODUCT_ID));
  }

  @Override
  public ProductConfirmDto confirmOrder(PageData pageData) {

    JSONArray buyInfo = JSONArray.parseArray(pageData.getAsString(ProductConstant.BUY_INFO));
    Map<String,String> buyInfoMap = listToMap(buyInfo);
    List<ProductStockModel> stocks = appProductStockMapper.findStockByIds(new ArrayList<>(buyInfoMap.keySet()));
    stocks.stream().forEach(item ->
            item.setNumber(Integer.parseInt(buyInfoMap.getOrDefault(item.getId(),"1")))
    );
    UserAddressModel addressModel = appAddressMapper.findDefaultAddress(pageData.getAsInteger(UserConstant.USERID));
    BigDecimal  totalMoney = stocks.stream().map(ProductStockModel::getPrice).reduce(BigDecimal.valueOf(0),BigDecimal::add);
    IntSummaryStatistics totalNumberStatistics = stocks.stream().collect(Collectors.summarizingInt(ProductStockModel::getNumber));

    LOGGER.info("确认订单接口：选择商品总价格{}",totalMoney);
    return ProductConfirmDto.builder().setProductListDto(stocks).setUserAddressModel(addressModel)
            .setTotalMoney(totalMoney).setTotalNumber((int)totalNumberStatistics.getSum());
  }
  @Override
  public ProductPayDto submitOrder(PageData pageData) {
    ProductOrderModel productOrderModel =  ProductOrderModel.builder(pageData);
    LOGGER.info("创建订单数据为：", MapperUtil.toJson(productOrderModel));
    appProductOrderMapper.createOrder(productOrderModel);
    JSONArray buyInfo = JSONArray.parseArray(pageData.getAsString(ProductConstant.BUY_INFO));
    LOGGER.info("用户购买信息为{}",buyInfo);
    Map<String,String> buyInfoMap = listToMap(buyInfo);
    List<ProductOrderGoodsModel> orderGoodsModels = appProductStockMapper.findProductInfoByStuIds(new ArrayList<>(buyInfoMap.keySet()));
    orderGoodsModels.stream().forEach(item -> {
      item.setOrderId(productOrderModel.getId());
      item.setProductCount(buyInfoMap.get(item.getSkuId()));
    });
    appProductOrderMapper.createOrderGoods(orderGoodsModels);
    ProductPayDto payDto = ProductPayDto.builder().setOrderSn(productOrderModel.getOrderSn());
    //动态建立任务调度 30分钟后执行更改订单状态
    try {
      TimerTaskModel timerTaskModel = new TimerTaskModel();
      timerTaskModel.setCreateTime(TimeUtils.toStringFormat_1(new Date()));
      timerTaskModel.setJobData(MapperUtil.toJson(productOrderModel));
      timerTaskModel.setDescription("更改未支付状态为取消状态");
      timerTaskModel.setIsRunning(false);
      timerTaskModel.setJobStatus(TimerTaskModel.CONCURRENT_IS);
      timerTaskModel.setPlanStatus(TimerTaskModel.CONCURRENT_IS);
      timerTaskModel.setName(productOrderModel.getOrderSn());
      timerTaskModel.setGroupName(TimerTaskConstant.GROUP_NAME_ORDER);
      timerTaskModel.setSpringId(ConfigureManager.getAppConfig().getPuroductConfig().getNoPayTaskClassName());
      timerTaskModel.setMethodName(ConfigureManager.getAppConfig().getPuroductConfig().getDefaultMethodName());
      timerTaskModel.setJobType(JobType.NOPAY_ORDER_JOB.getCode());
      quartzManager.addJob(new TimerTaskModel());
    }catch (Exception e){
      LOGGER.error("订单号为{}的定时任务添加失败",productOrderModel.getOrderSn());
    }
    return payDto;
  }

  private Map<String,String> listToMap(JSONArray dest){
   return  dest.stream().collect(Collectors.toMap(
            p->{JSONObject buyItem = JSONObject.parseObject(p.toString()) ;   return buyItem.getString(ProductConstant.SKU_ID);}
            ,p-> {JSONObject buyItem = JSONObject.parseObject(p.toString()) ; return buyItem.getString(ProductConstant.BUY_NUM);}));
  }
}
