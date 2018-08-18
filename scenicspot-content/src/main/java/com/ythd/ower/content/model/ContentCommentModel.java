package com.ythd.ower.content.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContentCommentModel implements Serializable{

    private Integer id;

    private String userPhoto;

    private String author;

    private String content;

    private String putime;

    private String userId;

    private String contentId;

    private String status;

}
