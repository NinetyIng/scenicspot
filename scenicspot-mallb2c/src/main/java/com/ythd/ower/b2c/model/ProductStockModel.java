package com.ythd.ower.b2c.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品库存模型
 * Created by junbo
 * on 2018/8/19
 */
@Data
public class ProductStockModel  implements Serializable{

    private Integer id;

    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 销售价格
     */
    private BigDecimal price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 销售属性
     */
    private String saleAttr;

    /**
     * 商品编码
     */
    private String barCode;

    /**
     * 库存预警值
     */
    private String stockAlarm;

}
