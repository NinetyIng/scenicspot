package com.ythd.ower.common.constants;

import com.ythd.ower.common.dto.ErrorCode;

public interface ErrorCodesContants {

    ErrorCode  PARAM_ERROR = new ErrorCode("yt_002","参数错误");

    ErrorCode  CONFIG_ERROR = new ErrorCode("yt_003","配置读取失败");

    ErrorCode  SYSTEM_CONFIG_ERROR = new ErrorCode("yt_301","系统配置读取失败");

    ErrorCode  WX_CONFIG_ERROR = new ErrorCode("yt_302","微信配置读取失败");

    ErrorCode  APP_CONFIG_ERROR = new ErrorCode("yt_302","应用配置读取失败");

    ErrorCode  SYSTEM_ERROR = new ErrorCode("yt_001","系统异常");

    ErrorCode  LOGIN_EXCEPTION = new ErrorCode("yt_201","用户登录异常，请退出重新登录");

    ErrorCode  USER_NOEXIST_EXCEPTION = new ErrorCode("yt_202","用户不存在，请重新关注");

    ErrorCode  BUYER_LIMIT_EXCEPTION = new ErrorCode("yt_203","用户购买行为已被管理员限制，请联系平台客服");

}
