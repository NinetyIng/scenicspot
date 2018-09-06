package com.ythd.ower.b2c.constant;

import lombok.Data;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public enum  CreateType {
  WECHAT(1,"微信下单"),PC(2,"PC下单");
  private Integer code;

  private String desc;

  CreateType(Integer code,String desc){
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
