package com.ythd.ower.b2c.mapper;

import com.ythd.ower.b2c.model.ProductStockModel;

import java.util.List;

/**
 * Created by junbo
 * on 2018/8/19
 */
public interface AppProductStockMapper {

    List<ProductStockModel> findStockListByGoodsId(Integer productId);

}
