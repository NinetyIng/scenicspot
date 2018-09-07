package com.ythd.ower.b2c.dto;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public class ProductPayDto {
  /**
   * 订单号
   */
  String orderSn;

  public static ProductPayDto builder(){
    return new ProductPayDto();
  }

  public String getOrderSn() {
    return orderSn;
  }

  public ProductPayDto setOrderSn(String orderSn) {
    this.orderSn = orderSn;
    return this;
  }
}
