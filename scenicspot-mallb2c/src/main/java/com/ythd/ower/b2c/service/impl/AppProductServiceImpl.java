package com.ythd.ower.b2c.service.impl;

import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.dto.ProductConfirmDto;
import com.ythd.ower.b2c.dto.ProductPayDto;
import com.ythd.ower.b2c.mapper.AppProductCommentMapper;
import com.ythd.ower.b2c.mapper.AppProductMapper;
import com.ythd.ower.b2c.mapper.AppProductOrderMapper;
import com.ythd.ower.b2c.mapper.AppProductStockMapper;
import com.ythd.ower.b2c.model.ProductModel;
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
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.mapper.AppAddressMapper;
import com.ythd.ower.member.model.UserAddressModel;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    String ids = pageData.getAsString(ProductConstant.STOCK_IDS);
    List<ProductStockModel> stocks = appProductStockMapper.findStockByIds(CollectionUtils.arrayToList(ids.split(SpecificSymbolConstants.COMMA)));
    UserAddressModel addressModel = appAddressMapper.findDefaultAddress(pageData.getAsInteger(UserConstant.USERID));
    return ProductConfirmDto.builder().setProductListDto(stocks).setUserAddressModel(addressModel);
  }


  @Override
  public ProductPayDto submitOrder(PageData pageData) {
    ProductOrderModel productOrderModel =  ProductOrderModel.builder(pageData);
    LOGGER.info("创建订单数据为：", MapperUtil.toJson(productOrderModel));
    appProductOrderMapper.createOrder(productOrderModel);
    return null;
  }
}
