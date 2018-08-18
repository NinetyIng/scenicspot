package com.ythd.ower.common.config;

import com.ythd.ower.common.constants.SpecificSymbolConstants;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: 微信变量全局配置
 *
 * @author: liujunbo
 * @since: 2018/8/16
 * @version: $Revision$
 */
public class WxConfigure {

  private String appId;

  private String appKey;

  private String mchtId;

  private WxPayConfigure payConfigure;

  private WxUrlConfigure urlConfigure;

  public WxPayConfigure builderWxPayConfigureInstance(){
    this.payConfigure =  new WxPayConfigure();
    return payConfigure;
  }

  public WxUrlConfigure builderWxUrlConfigureInstance(){
    this.urlConfigure =  new WxUrlConfigure();
    return urlConfigure;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getMchtId() {
    return mchtId;
  }

  public void setMchtId(String mchtId) {
    this.mchtId = mchtId;
  }

  public WxPayConfigure getPayConfigure() {
    return payConfigure;
  }

  public void setPayConfigure(WxPayConfigure payConfigure) {
    this.payConfigure = payConfigure;
  }

  public WxUrlConfigure getUrlConfigure() {
    return urlConfigure;
  }

  public void setUrlConfigure(WxUrlConfigure urlConfigure) {
    this.urlConfigure = urlConfigure;
  }


  public class WxPayConfigure{

    private String payAppId;

    private String payAppKey;

    private String payMchtId;

    private String notifyUrl;

    private String payTitle;

    public String getPayTitle() {
      return payTitle;
    }

    public WxPayConfigure setPayTitle(String payTitle) {
      this.payTitle = payTitle;
      return this;
    }

    public String getPayAppId() {
      return payAppId;
    }

    public WxPayConfigure setPayAppId(String payAppId) {
      this.payAppId = payAppId;
      return this;
    }

    public String getPayAppKey() {
      return payAppKey;
    }

    public WxPayConfigure setPayAppKey(String payAppKey) {
      this.payAppKey = payAppKey;
      return this;
    }

    public String getPayMchtId() {
      return payMchtId;
    }

    public WxPayConfigure setPayMchtId(String payMchtId) {
      this.payMchtId = payMchtId;
      return this;
    }

    public String getNotifyUrl() {
      return notifyUrl;
    }

    public WxPayConfigure setNotifyUrl(String notifyUrl) {
      this.notifyUrl = notifyUrl;
      return this;
    }
  }

  public class WxUrlConfigure{
    private static final String APPID = "{appid}";
    private static final String SECRET = "{secret}";
    private static final String ACCESS_TOKEN = "{access_token}";
    private static final String OPENID = "{openid}";
    private static final String CODE = "{code}";
    //获取accessTokenurl
    private String  accessTokenUrl;
    //JSAPI凭证
    private String  jsapiUrl;
    //用户列表
    private String userListUrl;
    //用户基本信息
    private String userInfoUrl;

    //网页授权获取用户openid
    private String openIdUrl;

    //创建菜单url
    private String createMenu;

    //模板列表url
    private String templateListUrl;

    private String sendTemplateUrl;

    //菜单列表
    private String menuInfoUrl;

    //推广二维码请求地址
    private String tgqrcodeUrl;


    //统一下单url
    private String unifiedOrderUrl;

    public String getAccessTokenUrl(String appid, String secret,String code) {
      if(StringUtils.isEmpty(code)){
        return accessTokenUrl.replace(APPID,appid).replace(SECRET,secret);
      }
      return accessTokenUrl.replace(APPID,appid).replace(SECRET,secret) + "&code="+code+"&grant_type=authorization_code";
    }

    public WxUrlConfigure setAccessTokenUrl(String accessTokenUrl) {
      this.accessTokenUrl = accessTokenUrl;
      return this;
    }

    public String getUserListUrl(String accessToken,String nextOpenid) {
      if(StringUtils.isEmpty(nextOpenid)){
        return userListUrl.replace(ACCESS_TOKEN,accessToken);
      }else{
        return String.join(SpecificSymbolConstants.AMPERSAND,userListUrl.replace(ACCESS_TOKEN,accessToken),"next_openid="+nextOpenid) ;
      }
    }

    public WxUrlConfigure setUserListUrl(String userListUrl) {
      this.userListUrl = userListUrl;
      return this;
    }

    public String getJsapiUrl(String accessToken) {
      return jsapiUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public WxUrlConfigure setJsapiUrl(String jsapiUrl) {
      this.jsapiUrl = jsapiUrl;
      return this;
    }

    public String getUserInfoUrl(String accessToken,String openId) {
      return userInfoUrl.replace(ACCESS_TOKEN,accessToken).replace(OPENID,openId);
    }

    public WxUrlConfigure setUserInfoUrl(String userInfoUrl) {
      this.userInfoUrl = userInfoUrl;
      return this;
    }

    public String getOpenIdUrl(String appId,String secret,String code) {
      return openIdUrl.replace(APPID,appId).replace(SECRET,secret).replace(CODE,code);
    }

    public WxUrlConfigure setOpenIdUrl(String openIdUrl) {
      this.openIdUrl = openIdUrl;
      return this;
    }

    public String getCreateMenu(String accessToken) {
      return createMenu.replace(ACCESS_TOKEN,accessToken);
    }

    public WxUrlConfigure setCreateMenu(String createMenu) {
      this.createMenu = createMenu;
      return this;
    }

    public String getTemplateListUrl(String accessToken) {
      return templateListUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public WxUrlConfigure setTemplateListUrl(String templateListUrl) {
      this.templateListUrl = templateListUrl;return this;
    }

    public String getSendTemplateUrl(String accessToken){
      return sendTemplateUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public WxUrlConfigure setSendTemplateUrl(String sendTemplateUrl) {
      this.sendTemplateUrl = sendTemplateUrl;return this;
    }

    public String getMenuInfoUrl(String accessToken) {
      return menuInfoUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public String getTgqrcodeUrl(String accessToken) {
      return tgqrcodeUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public WxUrlConfigure setTgqrcodeUrl(String tgqrcodeUrl) {
      this.tgqrcodeUrl = tgqrcodeUrl; return this;
    }

    public WxUrlConfigure setMenuInfoUrl(String menuInfoUrl) {
      this.menuInfoUrl = menuInfoUrl;return this;
    }

    public String getUnifiedOrderUrl() {
      return unifiedOrderUrl;
    }

    public WxUrlConfigure setUnifiedOrderUrl(String unifiedOrderUrl) {
      this.unifiedOrderUrl = unifiedOrderUrl;return this;
    }
  }
}
