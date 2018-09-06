package com.ythd.ower.b2c.mapper;

import com.ythd.ower.b2c.model.ProductStockModel;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by junbo
 * on 2018/8/19
 */
public interface AppProductStockMapper {

    List<ProductStockModel> findStockListByGoodsId(Integer productId);

    ProductStockModel findByAttrValues(@Param("attrVals") String attrVals,@Param("productId") String productId);

    List<ProductStockModel> findStockByIds(@Param("idList") List<String> idList);

}
