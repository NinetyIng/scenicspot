package com.ythd.ower.content.dto;


import com.ythd.ower.content.model.ContentModel;

import java.io.Serializable;
import java.util.List;

public class ContentListDto implements Serializable{

    private List<ContentModel> fixTopList;

    private List<ContentModel> contentList;

    public List<ContentModel> getFixTopList() {
        return fixTopList;
    }

    public static ContentListDto builder(){
        return new ContentListDto();
    }

    public ContentListDto setFixTopList(List<ContentModel> fixTopList) {
        this.fixTopList = fixTopList;
        return this;
    }

    public List<ContentModel> getContentList() {
        return contentList;
    }

    public ContentListDto setContentList(List<ContentModel> contentList) {
        this.contentList = contentList;
        return this;
    }
}
