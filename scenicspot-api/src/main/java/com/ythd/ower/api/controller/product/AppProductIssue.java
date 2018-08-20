package com.ythd.ower.api.controller.product;

import com.ythd.ower.api.check.ProductParamCheck;
import com.ythd.ower.api.dto.ProductIndexDto;
import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.b2c.service.AppProductService;
import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.ibox.Result;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.content.dto.ContentListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by junbo
 * on 2018/8/19
 */
@RestController
@RequestMapping("/api/product/")
public class AppProductIssue {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppProductIssue.class);

    private static final String LIMIT = "limit";

    @Autowired
    private AppProductService appProductService;

    /**
     * 商品首页接口
     * 数据结构  广告位  人气推荐列表  猜你喜欢列表
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public GenericResponseDto index(@RequestBody PageData requestParam) {
        LOGGER.info("请求商品首页接口，请求参数：{}", MapperUtil.toJson(requestParam));
        //构建首页数据结构
        ProductIndexDto productIndexDto = ProductIndexDto.builder();
        requestParam.put(ProductConstant.RECOMMEND_ORDER,"1");
        requestParam.put(LIMIT, ConfigureManager.getAppConfig().getPuroductConfig().getIndexRecommendCount());
        productIndexDto.setRecommeds(appProductService.productList(requestParam));
        requestParam.remove(ProductConstant.RECOMMEND_ORDER);
        requestParam.put(ProductConstant.PUTIME_ORDER,"1");
        requestParam.put(LIMIT, ConfigureManager.getAppConfig().getPuroductConfig().getIndexLastCount());
        productIndexDto.setLatestRelease(appProductService.productList(requestParam));
        LOGGER.info("商品首页接口响应数据为{}", MapperUtil.toJson(productIndexDto));
        return DtoUtils.getSuccessResponse(MapperUtil.toMap(MapperUtil.toJson(productIndexDto)));
    }

    /**
     * 商品列表接口 分页请求
     * @param requestParam
     * @return
     */
   @RequestMapping(value = "list", method = RequestMethod.POST)
   public GenericResponseDto list(@RequestBody PageData requestParam){
       LOGGER.info("请求商品列表接口，请求参数：{}", MapperUtil.toJson(requestParam));
       //参数校验 后续自己添加
      // Result<GenericResponseDto> checkResult = ProductParamCheck.

       //构建首页数据结构
       List<ProductModel> productList = appProductService.productListByPage(requestParam);
       LOGGER.info("商品首页接口响应数据为{}", MapperUtil.toJson(productList));
       return DtoUtils.getSuccessResponse(MapperUtil.toMap(MapperUtil.toJson(productList)));
   }

    /**
     * 商品详情页面
     * @param requestParam
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
   public GenericResponseDto detail(@RequestBody PageData requestParam){
       LOGGER.info("请求商品详情接口，请求参数：{}", MapperUtil.toJson(requestParam));
       Result<GenericResponseDto> checkResult = ProductParamCheck.checkProductDetailParam(requestParam);
       if(checkResult.isFailure()){
           return checkResult.getData();
       }
       ProductModel productModel = appProductService.productDetail(requestParam);
       LOGGER.info("请求商品详情接口返回数据：{}", MapperUtil.toJson(productModel));
       return DtoUtils.getSuccessResponse(MapperUtil.toMap(MapperUtil.toJson(productModel)));
   }

}
