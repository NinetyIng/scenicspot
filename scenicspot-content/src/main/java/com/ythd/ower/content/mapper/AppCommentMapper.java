package com.ythd.ower.content.mapper;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.content.model.ContentCommentModel;

import java.util.List;

public interface AppCommentMapper {

    /**
     * 评论列表按平列时间排序
     * @param page
     * @return
     */
    List<ContentCommentModel> findCommentListByPage(Page page);


    /**
     * 添加一条评论
     * @param model
     */
    void addComment(ContentCommentModel model);

    /**
     * 添加一条点赞
     * @param pageData
     */
    void addPrase(PageData pageData);

}
