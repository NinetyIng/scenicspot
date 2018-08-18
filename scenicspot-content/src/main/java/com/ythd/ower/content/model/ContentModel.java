package com.ythd.ower.content.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容对象实体
 */
@Data
public class ContentModel implements Serializable{

    private Integer id;

    /**
     * 标题
     */
    private String title;
    /**
     * 标题简称
     */
    private String sortTitle;
    /**
     * 广告链接 如内容广告的链接
     */
    private String link;
    /**
     * 摘要
     */
    private String abstractContent;

    /**
     * 列表图片
     */
    private String timg;

    /**
     * 类型
     */
    private String ctype;

    /**
     * 发布时间
     */
    private String puttime;

    /**
     * 来源
     */
    private String  orgin;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 内容类型
     */
    private String modelType;

    /**
     * 评论总数
     */
    private Integer commentCount;

    /**
     * 点赞总数
     */
    private Integer praiseCount;

    /**
     * 相册
     */
    private ContentAlbumModel album;
}
