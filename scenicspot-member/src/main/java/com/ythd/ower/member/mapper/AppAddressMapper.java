package com.ythd.ower.member.mapper;

import com.ythd.ower.member.model.UserAddressModel;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public interface AppAddressMapper {

  UserAddressModel findDefaultAddress(@Param("userId") Integer userId);

}
