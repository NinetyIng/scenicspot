package com.ythd.ower.member.mapper;

import com.ythd.ower.member.model.UserModel;
import org.apache.ibatis.annotations.Param;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
public interface AppUserMapper {

  void insert(UserModel userModel);

  UserModel findByOpenId(@Param("openId")String openId);

  UserModel findById(@Param("userId")String userId);

  void update (UserModel userModel);

}
