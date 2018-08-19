package com.ythd.ower.b2c.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by junbo
 * on 2018/8/19
 */
@Data
public class ProductAttrModel implements Serializable{

    private Integer id;

    private Integer goodsId;

    private String attrName;

    private String attrValues;

    private Integer attrSort;

    private Integer isSale;

}
