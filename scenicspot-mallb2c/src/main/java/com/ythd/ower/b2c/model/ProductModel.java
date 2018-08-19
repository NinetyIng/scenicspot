package com.ythd.ower.b2c.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by junbo
 * on 2018/8/19
 */
@Data
public class ProductModel implements Serializable{

    private String id;

    /**
     * 商品货号
     */
    private String goodsSn;

    /**
     * 商品名字
     */
    private String goodsName;

    /**
     * 商品标签
     */
    private String goodsTags;

    /**
     * 商品规格： 黄色|25G
     */
    private String goodsSpec;

    /**
     * 商品图文描述
     */
    private String goodsDesc;
    /**
     * 是否相册作为图文描述
     */
    private Integer asAlumbDesc;

    /**
     * 商品摘要
     */
    private String goodsSummary;

    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 售价
     */
    private BigDecimal shopPrice;

    /**
     * 库存数量
     */
    private Integer goodsStock;

    /**
     * 商品浏览量
     */
    private Integer clickCount;

    /**
     * 列表图片
     */
    private String listImg;

    /**
     * 销量
     */
    private Integer virtualSales;

    /**
     * 推荐值
     */
    private Integer recommend;

    /**
     * 人气值
     */
    private Integer popular;

    /**
     * 商品运费
     */
    private BigDecimal goodsFreight;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 上架时间
     */
    private String putime;


    /**
     * 商品相册
     */
    private List<ProductAlbumModel> album;

    /**
     * 商品属性
     */
    private List<ProductAttrModel> attrs;


}
