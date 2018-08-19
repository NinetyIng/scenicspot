package com.ythd.ower.b2c.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品相册
 * Created by junbo
 * on 2018/8/19
 */
@Data
public class ProductAlbumModel implements Serializable {

    private Integer id;

    private Integer goodsId;
    /**
     * 原图路径
     */
    private String originalImg;
    /**
     * 缩略图路径
     */
    private String thumbImg;
}
