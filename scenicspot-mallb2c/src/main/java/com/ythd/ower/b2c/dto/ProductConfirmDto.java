package com.ythd.ower.b2c.dto;

import com.ythd.ower.b2c.model.ProductStockModel;
import com.ythd.ower.member.model.UserAddressModel;
import java.util.List;

/**
 * Description: 确认订单dto
 *
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public class ProductConfirmDto {

  private List<ProductStockModel> stockList;

  private UserAddressModel userAddressModel;


  public static ProductConfirmDto builder(){
    return new ProductConfirmDto();
  }

  public List<ProductStockModel> getStockList() {
    return stockList;
  }

  public ProductConfirmDto setProductListDto(List<ProductStockModel> stockList) {
    this.stockList = stockList;
    return this;
  }

  public UserAddressModel getUserAddressModel() {
    return userAddressModel;
  }

  public ProductConfirmDto setUserAddressModel(UserAddressModel userAddressModel) {
    this.userAddressModel = userAddressModel;
    return this;
  }
}
