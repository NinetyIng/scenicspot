package com.ythd.ower.common.constants;

import com.ythd.ower.common.dto.ErrorCode;

public interface ErrorCodesContants {

    ErrorCode  PARAM_ERROR = new ErrorCode("yt_002","参数错误");

    ErrorCode  CONFIG_ERROR = new ErrorCode("yt_003","配置读取失败");

    ErrorCode  SYSTEM_CONFIG_ERROR = new ErrorCode("yt_301","系统配置读取失败");

    ErrorCode  WX_CONFIG_ERROR = new ErrorCode("yt_302","微信配置读取失败");

    ErrorCode  APP_CONFIG_ERROR = new ErrorCode("yt_302","应用配置读取失败");

    ErrorCode  SYSTEM_ERROR = new ErrorCode("yt_001","系统异常");

}
