package com.ythd.ower.content.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容相册
 */
@Data
public class ContentAlbumModel implements Serializable {

    private String id;
    /**
     * 原图路径
     */
    private String originalImg;

    /**
     * 缩略图路径
     */
    private String thumbnailImg;


}
