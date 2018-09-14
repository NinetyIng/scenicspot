package com.ythd.ower.api.check;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.b2c.service.impl.AppProductServiceImpl;
import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.constants.SpecificSymbolConstants;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.ibox.Result;
import com.ythd.ower.member.constant.UserConstant;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 商品接口 参数校验
 * Created by junbo
 * on 2018/8/19
 */
public class ProductParamCheck {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductParamCheck.class);


  public interface Regular {
    String POSITIVE_INTEGER = "^\\d+$";

    String POSITIVE_MONEY = "^(([1-9]{1}\\\\d*)|([0]{1}))(\\\\.(\\\\d){0,2})?$";

    String POSITIVE_PHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\\\d{8}$";

  }

  public static Result<GenericResponseDto> checkProductDetailParam(PageData pageData) {
    if (StringUtils.isEmpty(pageData.getAsString(ProductConstant.PRODUCT_ID)) || !pageData.getAsString(ProductConstant.PRODUCT_ID).matches(Regular.POSITIVE_INTEGER)) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    return Result.success();
  }
  public static Result<GenericResponseDto> checkIdParam(PageData pageData) {
    if (StringUtils.isEmpty(pageData.getAsString(UserConstant.ID)) || !pageData.getAsString(UserConstant.ID).matches(Regular.POSITIVE_INTEGER)) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    return Result.success();
  }




  public static Result<GenericResponseDto> checkAddressListParam(PageData pageData) {
    if (StringUtils.isEmpty(pageData.getAsString(UserConstant.USERID)) || !pageData.getAsString(UserConstant.USERID).matches(Regular.POSITIVE_INTEGER)) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    return Result.success();
  }


  public static Result<GenericResponseDto> checkAddAddressParam(PageData pageData){
    if (StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.CITYID))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.CITY))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.AREA))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.AREAID))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.PROVINCEID))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.PROVINCE))
            ||  StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.CONADDRESS))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.CONNAME))
            || StringUtils.isNotEmpty(pageData.getAsString(UserConstant.Filed.CONPHONE))){
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));

    }
    return Result.success();
  }


  public static Result<GenericResponseDto> checkStockDetail(PageData pageData) {
    if (StringUtils.isEmpty(pageData.getAsString(ProductConstant.ATTR_VALS))) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    if (StringUtils.isEmpty(pageData.getAsString(ProductConstant.PRODUCT_ID)) || !pageData.getAsString(ProductConstant.PRODUCT_ID).matches(Regular.POSITIVE_INTEGER)) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    return Result.success();
  }


  public static Result<GenericResponseDto> checkProductList(PageData pageData) {
    return Result.success();
  }

  public static Result<GenericResponseDto> checkConfirmOrder(PageData pageData) {
    String buyInfo = pageData.getAsString(ProductConstant.BUY_INFO);
    if (StringUtils.isEmpty(buyInfo)) {
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    JSONArray buyInfoAry = JSONObject.parseArray(buyInfo);
    //检查商品是否超出单个商品的购买限制
    if(buyInfoAry.stream().anyMatch(item -> {
      JSONObject buyItem = JSONObject.parseObject(item.toString());
      return ConfigureManager.getAppConfig().getPuroductConfig().getSingleProductBuyLimit().intValue()
              < Integer.parseInt(buyItem.getString(ProductConstant.BUY_NUM));
    })){
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.BUY_NUM_LIMIT));
    }
    //检查是否超出购买商品个数限制
    if(ConfigureManager.getAppConfig().getPuroductConfig().getBuyCountLimit().intValue() < buyInfoAry.size()){
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.BUY_PRODUCTNUM_LIMIT));
    }
    return Result.success();
  }

  public static Result<GenericResponseDto> checkSubmitOrder(PageData pageData) {

    String orderMoney = pageData.getAsString(ProductConstant.ORDER_MONEY);

    String productMoney = pageData.getAsString(ProductConstant.PRODUCT_MONEY);

    String contactName = pageData.getAsString(ProductConstant.CONNAME);

    String contactPhone = pageData.getAsString(ProductConstant.CONPHONE);

    String address = pageData.getAsString(ProductConstant.CONADDRESS);

    if(StringUtils.isEmpty(orderMoney) || !orderMoney.matches(Regular.POSITIVE_MONEY)){
      LOGGER.error("提交订单接口 参数检查失败，订单总金额格式不正确");
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    if(StringUtils.isEmpty(productMoney) || !productMoney.matches(Regular.POSITIVE_MONEY)){
      LOGGER.error("提交订单接口 参数检查失败，商品总金额格式不正确");
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    if(StringUtils.isEmpty(contactPhone) || !productMoney.matches(Regular.POSITIVE_PHONE)){
      LOGGER.error("提交订单接口 参数检查失败，手机号格式不正确");
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    if(StringUtils.isEmpty(contactName) || contactName.length() > 6){
      LOGGER.error("提交订单接口 参数检查失败，联系人姓名不能为空且应小于6个字符");
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    if(StringUtils.isEmpty(address)){
      LOGGER.error("提交订单接口 参数检查失败，地址不能为空");
      return Result.failure(DtoUtils.getFailResponse(ErrorCodesContants.PARAM_ERROR));
    }
    return Result.success();
  }
  public static void main(String[] args) {
    System.out.println("34".matches(Regular.POSITIVE_INTEGER));
  }

}
