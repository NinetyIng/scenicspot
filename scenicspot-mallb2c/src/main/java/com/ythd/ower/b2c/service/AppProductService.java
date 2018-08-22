package com.ythd.ower.b2c.service;

import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.b2c.model.ProductStockModel;
import com.ythd.ower.common.dto.PageData;

import java.util.List;

public interface AppProductService {

    /**
     * 分页查询商品列表数据
     * @param requestParam
     * @return
     */
    List<ProductModel> productListByPage(PageData requestParam);

    /**
     * 查询商品列表数据
     * @param requestParam
     * @return
     */
    List<ProductModel> productList(PageData requestParam);


    ProductModel productDetail(PageData requestParam);


    ProductStockModel stockDetail(PageData pageData);

}
