package com.ythd.ower.content.dto;

import com.ythd.ower.content.model.ContentCommentModel;
import com.ythd.ower.content.model.ContentModel;

import java.util.List;

public class ContentDetailDto {

    private ContentModel contentModel;

    private List<ContentCommentModel> comments;


    public static ContentDetailDto builder(){
        return new ContentDetailDto();
    }
    public ContentModel getContentModel() {
        return contentModel;
    }

    public ContentDetailDto setContentModel(ContentModel contentModel) {
        this.contentModel = contentModel;
        return this;
    }

    public List<ContentCommentModel> getComments() {
        return comments;
    }

    public ContentDetailDto setComments(List<ContentCommentModel> comments) {
        this.comments = comments;
        return this;
    }
}
