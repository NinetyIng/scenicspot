package com.ythd.ower.b2c.service;

import com.ythd.ower.b2c.dto.ProductConfirmDto;
import com.ythd.ower.b2c.dto.ProductPayDto;
import com.ythd.ower.b2c.model.ProductModel;
import com.ythd.ower.b2c.model.ProductStockModel;
import com.ythd.ower.common.dto.PageData;

import com.ythd.ower.member.model.UserAddressModel;
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

    /**
     * 商品详情
     * @param requestParam
     * @return
     */
    ProductModel productDetail(PageData requestParam);

    /**
     * 库存详情
     * @param pageData
     * @return
     */
    ProductStockModel stockDetail(PageData pageData);

    /**
     * 确认订单
     * @param pageData
     * @return
     */
    ProductConfirmDto confirmOrder(PageData pageData);
    /**
     * 提交订单 返回支付参数
     * @param pageData
     * @return
     */
    ProductPayDto submitOrder(PageData pageData);

}
