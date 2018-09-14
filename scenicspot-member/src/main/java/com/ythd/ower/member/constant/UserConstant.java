package com.ythd.ower.member.constant;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/6
 * @version: $Revision$
 */
public interface UserConstant {

  String TIMEFORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

  String USERID = "userId";

  String ID = "id";

  String OPENID = "open_id";

  String WX_USERINFO = "wxUserInfo";


  String WX_HEADIMGURL = "headimgurl";

  String WX_NICKNAME = "nickname";

  String WX_PHONE = "phone";

  String WX_TITLE = "微信公众号";

  String SUBSCRIBE_TIME = "subscribe_time";

  String USER_MODEL = "userModel";

  Integer OPENED = 1;

  Integer CLOSED = 0;


  interface  Filed{
    String CONPHONE = "conPhone";
    String CONNAME = "conName";
    String CONADDRESS = "conAddress";
    String PROVINCEID = "provinceId";
    String CITYID = "cityId";
    String AREAID = "areaId";
    String PROVINCE = "province";
    String CITY = "city";
    String AREA = "area";
    String DEF = "def";
  }

}
