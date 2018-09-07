package com.ythd.ower.b2c.mapper;

import com.ythd.ower.b2c.model.ProductOrderGoodsModel;
import com.ythd.ower.b2c.model.ProductOrderModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public interface AppProductOrderMapper {
  /**
   * 创建订单 返回订单id
   * @param order
   */
  void createOrder(ProductOrderModel order);


  void createOrderGoods(@Param("list") List<ProductOrderGoodsModel> list);
}
