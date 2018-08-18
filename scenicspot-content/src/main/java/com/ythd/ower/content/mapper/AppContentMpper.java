package com.ythd.ower.content.mapper;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.content.model.ContentModel;

import java.util.List;

public interface AppContentMpper {

    /**
     * 查询新闻列表非头条  先按推荐值排序
     * 后按时间排序(过滤掉头条内容可传参数excludeIds)
     * @param page
     * @return
     */
    List<ContentModel> findContentListByPage(Page page);

    /**
     * 查询固顶新闻 可以为多条 固顶日期为空标识 不限制固顶时间
     * 先根据固顶级别排序再根据推荐值排序
     * @param pageData
     * @return
     */
    List<ContentModel> findFixTopList(PageData pageData);

    /**
     * 查询文章详情
     * @param pageData
     * @return
     */
    ContentModel findContentDetail(PageData pageData);

    /**
     * 点赞自增
     */
    void addParseCount();

    /**
     * 评论自增
     */
    void addCommentCount();

}
