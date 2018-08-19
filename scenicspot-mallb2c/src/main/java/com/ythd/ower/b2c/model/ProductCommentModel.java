package com.ythd.ower.b2c.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品评论
 * Created by junbo
 * on 2018/8/19
 */
@Data
public class ProductCommentModel implements Serializable {

    private Integer id;

    private Integer goodsId;

    private Integer userId;

    /**
     * 评论人
     */
    private String author;

    /**
     * 作者微信图像
     */
    private String authorPhoto;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String putime;

    /**
     * 星级
     */
    private Integer star;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 评论图片
     */
    private String commentImages;
}
