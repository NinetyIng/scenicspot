package com.ythd.ower.b2c.constant;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public enum  OrderStatus {

   WFK(0,"代付款"),PHZ(1,"配货中"),DFH(2,"代发货"),YFH(3,"已发货"),YSD(4,"已送达"),JYCG(5,"交易成功"),YQX(6,"已取消"),YWC(7,"已完成"),YGB(8,"已关闭");

  private Integer code;

  private String desc;

  OrderStatus(Integer code,String desc){
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

  public void setDesc(String desc) {
    this.desc = desc;
  }


}
