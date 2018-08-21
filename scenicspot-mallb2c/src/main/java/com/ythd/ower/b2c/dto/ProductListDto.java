package com.ythd.ower.b2c.dto;

import com.ythd.ower.b2c.model.ProductModel;
import java.util.List;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/8/21
 * @version: $Revision$
 */
public class ProductListDto {

  private List<ProductModel> productList;

  public static ProductListDto bulider(){
    return new ProductListDto();
  }

  public List<ProductModel> getProductList() {
    return productList;
  }

  public ProductListDto setProductList(List<ProductModel> productList) {
    this.productList = productList;
    return this;
  }
}
