package com.ythd.ower.content.service;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.content.dto.ContentDetailDto;
import com.ythd.ower.content.dto.ContentListDto;
import com.ythd.ower.content.model.ContentCommentModel;
public interface AppContentService {

    /**
     * 分页查询文章列表
     * @param page
     * @return
     */
    ContentListDto contentList(Page page);

    /**
     * 查询文章详情 包含评论
     * @param page
     * @return
     */
    ContentDetailDto contentDetail(Page page);

    /**
     * *增加一条评论
     * @param model
     */
    void addComment(ContentCommentModel model);

    /**
     * 增加一个点赞
     * @param pageData
     */
    void  addPrase(PageData pageData);


}
