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
public enum  OrderPayStatus {

  WFK(0,"未付款"),YFK(1,"已付款"),YQX(2,"已取消"),YTK(3,"已退款"),TKZ(4,"退款中");

  private Integer code;

  private String desc;

  OrderPayStatus(Integer code,String desc){
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
