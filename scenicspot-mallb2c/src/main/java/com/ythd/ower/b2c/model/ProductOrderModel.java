package com.ythd.ower.b2c.model;

import com.ythd.ower.b2c.constant.CreateType;
import com.ythd.ower.b2c.constant.OrderPayStatus;
import com.ythd.ower.b2c.constant.OrderStatus;
import com.ythd.ower.b2c.constant.ProductConstant;
import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.tools.IdentityFactoryUtils;
import com.ythd.ower.common.tools.TimeUtils;
import com.ythd.ower.member.constant.UserConstant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.ythd.ower.member.model.UserModel;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
@Data
public class ProductOrderModel  implements Serializable{

  private Integer id;

  private String orderSn;

  private Integer userId;

  private String userName;

  private BigDecimal orderMoney;

  private BigDecimal productMoney;

  private String orderStatus;

  private String shippingStatus;

  private String shippingType;

  private String payStatus;

  private String payType;

  private String contactName;

  private String contactPhone;

  private String provinceId;

  private String province;

  private String cityId;

  private String city;


  private String areaId;

  private String area;

  private String address;

  private String userNote;

  private String createTime;

  private String payTime;

  private String signTime;

  private String sendTime;

  private Integer mer;

  private String merType;

  private String merTitle;

  private String merNo;

  private String createType;

  private Integer delete;

  private String refund;

  private String refundStatus;

  private String failReason;


  public static ProductOrderModel builder(PageData pageData){
    UserModel userModel = (UserModel)pageData.get(UserConstant.USER_MODEL);
    if (userModel.getBuyer() == UserConstant.CLOSED){
      throw new BizServiceException(ErrorCodesContants.BUYER_LIMIT_EXCEPTION);
    }
    ProductOrderModel productOrderModel = new ProductOrderModel();
    productOrderModel.setUserId(userModel.getId());
    productOrderModel.setUserName(userModel.getNikeName());
    productOrderModel.setAddress(pageData.getAsString(ProductConstant.CONADDRESS));
    productOrderModel.setArea(pageData.getAsString(ProductConstant.AREA));
    productOrderModel.setAreaId(pageData.getAsString(ProductConstant.AREA_ID));
    productOrderModel.setCity(pageData.getAsString(ProductConstant.CITY));
    productOrderModel.setCityId(pageData.getAsString(ProductConstant.CITY_ID));
    productOrderModel.setContactName(pageData.getAsString(ProductConstant.CONNAME));
    productOrderModel.setContactPhone(pageData.getAsString(ProductConstant.CONPHONE));
    productOrderModel.setProvince(pageData.getAsString(ProductConstant.PROVINCE));
    productOrderModel.setProvinceId(pageData.getAsString(ProductConstant.PROVINCE_ID));
    productOrderModel.setCreateTime(TimeUtils.toStringFormat_1(new Date()));
    productOrderModel.setCreateType(StringUtils.isEmpty(pageData.getAsString(ProductConstant.CREATE_TYPE))
            ? CreateType.WECHAT.getDesc() : pageData.getAsString(ProductConstant.CREATE_TYPE));
    productOrderModel.setDelete(0);
    productOrderModel.setMer(pageData.getAsInteger(ProductConstant.MER) == null ? 0 : pageData.getAsInteger(ProductConstant.MER));
    productOrderModel.setMerNo(pageData.getAsString(ProductConstant.MER_NO));
    productOrderModel.setMerTitle(pageData.getAsString(ProductConstant.MER_TITLE));
    productOrderModel.setMerType(pageData.getAsString(ProductConstant.MER_TYPE));
    productOrderModel.setOrderStatus(String.valueOf(OrderStatus.WFK.getCode()));
    productOrderModel.setProductMoney(pageData.getAsBigDecimal(ProductConstant.PRODUCT_MONEY));
    productOrderModel.setOrderMoney(pageData.getAsBigDecimal(ProductConstant.ORDER_MONEY));
    productOrderModel.setOrderSn(IdentityFactoryUtils.createPk());
    productOrderModel.setPayStatus(String.valueOf(OrderPayStatus.WFK.getCode()));
    return productOrderModel;
  }
}
