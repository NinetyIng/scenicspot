package com.ythd.ower.member.model;

import lombok.Data;

/**
 * Description: 地址模型
 *
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
@Data
public class UserAddressModel {

  private Integer id;

  private String conPhone;

  private String conName;

  private String conAddress;

  private Integer provinceId;

  private Integer cityId;

  private Integer areaId;

  private String province;

  private String city;

  private String area;

  private Integer def;

}
