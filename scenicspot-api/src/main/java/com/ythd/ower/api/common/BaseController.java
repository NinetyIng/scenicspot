package com.ythd.ower.api.common;

import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.member.constant.UserConstant;
import com.ythd.ower.member.model.UserModel;
import com.ythd.ower.member.service.AppUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by junbo
 * on 2018/9/8
 */
public abstract class BaseController {

    private AppUserService appUserService;


    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public UserModel getUser() {
        String openId = (String)getRequest().getSession().getAttribute(UserConstant.OPENID);
        if (StringUtils.isEmpty(openId)){
          throw new BizServiceException(ErrorCodesContants.LOGIN_EXCEPTION);
        }
        UserModel userModel = appUserService.findUserByOpenId(openId);
        if (userModel == null){
            throw new BizServiceException(ErrorCodesContants.USER_NOEXIST_EXCEPTION);
        }
        return userModel;
    }

}
