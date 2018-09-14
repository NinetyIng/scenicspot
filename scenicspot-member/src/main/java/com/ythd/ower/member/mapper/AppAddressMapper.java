package com.ythd.ower.member.mapper;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.member.model.UserAddressModel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public interface AppAddressMapper {

  /**
   * 查询用户默认地址
   * @param userId
   * @return
   */
  UserAddressModel findDefaultAddress(@Param("userId") Integer userId);

  /**
   * 查询用户所有地址信息
   * @param userId
   * @return
   */
  List<UserAddressModel> findAllByUserId(@Param("userId") Integer userId);

  /**
   * 删除用户当前地址
   * @param id
   */
  void deleteById(@Param("id") Integer id);

  /**
   * 添加一条地址
   * @param pageData
   */
  void add(PageData pageData);



}
