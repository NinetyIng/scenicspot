package com.ythd.ower.b2c.mapper;

import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by junbo
 * on 2018/8/19
 */
public interface AppProductMapper {

    /**
     * 分页查询商品信息 不返回相册
     * 条件：IS_DELETE = 0 IS_ON_SALE = 1 list_img 不为空的
     * 排序：默认排序：推荐值 发布时间
     * @param page
     * @return
     */
    List<ProductModel> findProductByPage(Page page);

    /**
     * 不分页查询 固顶查询n条
     * @param pageData
     * @return
     */
    List<ProductModel> findProduct(PageData pageData);

    /**
     * 查询商品详情 但不返回库存信息
     * @param productId
     * @return
     */
    ProductModel findProductDetail(@Param("productId") Integer productId);
}
