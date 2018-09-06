package com.ythd.ower.b2c.constant;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public enum  OrderPayType {

  WECHAT_SM(0,"微信扫码支付"),WECHAT_GZH(1,"微信公众号"),ALIPAY(2,"支付宝");

  private Integer code;

  private String desc;

  OrderPayType(Integer code,String desc){
    this.code = code;
    this.desc =  desc;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

}
