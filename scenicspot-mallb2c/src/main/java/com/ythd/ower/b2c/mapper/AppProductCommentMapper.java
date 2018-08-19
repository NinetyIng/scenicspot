package com.ythd.ower.b2c.mapper;

import com.ythd.ower.b2c.model.ProductCommentModel;
import com.ythd.ower.common.dto.Page;

import java.util.List;

/**
 * Created by junbo
 * on 2018/8/19
 */
public interface AppProductCommentMapper {

    /**
     * 分页查询某个商品的评论列表
     * @param page
     * @return
     */
    List<ProductCommentModel> findCommentListByPage(Page page);

    /**
     * 添加评论
     * @param model
     */
    void addComment(ProductCommentModel model);
}
