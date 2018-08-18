package com.ythd.ower.wx.common;

/**
 * Description:
 * @author: liujunbo
 * @since: 2018/8/15
 * @version: $Revision$
 */
public interface WechatConstant {

  String ACCESS_TOKEN = "access_token";

  String REQUEST_METHOD_POST = "POST";
  String REQUEST_METHOD_GET = "GET";

  String RESPONSE_CODE = "errcode";

  String RESPONSE_MSG = "errmsg";

  String REQUEST_CHARSET = "UTF-8";


  interface SpecialSymbol{
    String SLASH = "/";

    String BACKSLASH ="\\";

    String AND = "&";

    String START = "*";

    String AATE = "@";

    String EQUEL = "=";



  }

}
