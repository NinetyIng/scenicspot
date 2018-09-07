package com.ythd.ower.b2c.model;

import lombok.Data;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
@Data
public class ProductOrderGoodsModel {

  private Integer id;

  private Integer orderId;

  private Integer productId;

  private String productName;

  private String productSn;

  private String productCount;

  private String productPrice;

  private Integer skuId;

  private String productAttr;

  private Integer refund;
}
