package com.ythd.ower.member.service;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.member.model.UserAddressModel;
import com.ythd.ower.member.model.UserModel;
import java.util.List;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public interface AppUserService {

  /**
   * 关注公众号
   * @param pd
   */
  void subscribe(PageData pd);

  /**
   * 取消关注公众号
   * @param openId
   */
  void unsubscribe(String openId);

  /**
   * 根据openid查询用户
   * @param openId
   * @return
   */
  UserModel findUserByOpenId(String openId);


  /**
   * 地址列表
   * @param pageData
   * @return
   */
  List<UserAddressModel> addressList(PageData pageData);

  /**
   * 删除地址
   * @param pageData
   */
  void  deleteAddress(PageData pageData);

  /**
   * 添加地址
   * @param pageData
   */
  void addAddress(PageData pageData);

}
