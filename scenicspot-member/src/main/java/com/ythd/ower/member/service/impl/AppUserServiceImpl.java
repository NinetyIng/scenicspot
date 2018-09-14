package com.ythd.ower.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.mapper.AppAddressMapper;
import com.ythd.ower.member.mapper.AppUserMapper;
import com.ythd.ower.member.model.UserAddressModel;
import com.ythd.ower.member.model.UserModel;
import com.ythd.ower.member.service.AppUserService;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/9/5
 * @version: $Revision$
 */
@Service
public class AppUserServiceImpl  implements AppUserService{

  private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);

  Lock lock = new ReentrantLock();


  @Resource
  private AppUserMapper appUserMapper;

  @Resource
  private AppAddressMapper appAddressMapper;


  @Override
  public void subscribe(PageData pd) {
    String openId = pd.getAsString(UserConstant.OPENID);
    if (StringUtils.isEmpty(openId)){
      LOGGER.error("用户关注公众号，但未获取该用户的openid");
      return;
    }
    JSONObject wxUserInfo = JSONObject.parseObject(pd.getString(UserConstant.WX_USERINFO));
    lock.lock();
    try {
      UserModel userModel = appUserMapper.findByOpenId(openId);
      if (userModel == null) {
        userModel = UserModel.builder();
      }
      userModel.setHeadimgurl(wxUserInfo.getString(UserConstant.WX_HEADIMGURL))
              .setNikeName(wxUserInfo.getString(UserConstant.WX_NICKNAME))
              .setSubscribeTime(pd.getAsString(UserConstant.SUBSCRIBE_TIME))
              .setBuyer(UserConstant.OPENED).setCommenter(UserConstant.OPENED).setSubscribe(UserConstant.OPENED)
              .setLoginer(UserConstant.OPENED)
              .setRegisterChannel(UserConstant.WX_TITLE).setOpenId(openId)
              .setPhone(wxUserInfo.getString(UserConstant.WX_PHONE));
      if (userModel.getId() != null) {
        appUserMapper.update(userModel);
      } else {
        appUserMapper.insert(userModel);
      }
    }catch (Exception e){
      LOGGER.error("用户关注入库出错，用户微信信息：{}",wxUserInfo);
    }finally {
      lock.unlock();
    }
  }
  @Override
  public void unsubscribe(String openId) {
    UserModel userModel = appUserMapper.findByOpenId(openId);
    if(userModel != null){
       userModel.setSubscribe(UserConstant.CLOSED);
       appUserMapper.update(userModel);
    }
  }

  @Override
  public UserModel findUserByOpenId(String openId) {
    return appUserMapper.findByOpenId(openId);
  }

  @Override
  public List<UserAddressModel> addressList(PageData pageData) {
    return appAddressMapper.findAllByUserId(pageData.getAsInteger(UserConstant.USERID));
  }

  @Override
  public void deleteAddress(PageData pageData) {
    appAddressMapper.deleteById(pageData.getAsInt(UserConstant.ID));
  }

  @Override
  public void addAddress(PageData pageData) {
    appAddressMapper.add(pageData);
  }
}
