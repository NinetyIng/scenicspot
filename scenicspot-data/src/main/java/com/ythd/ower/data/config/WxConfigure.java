package com.ythd.ower.data.config;

import lombok.Data;

/**
 * Description: 微信变量全局配置
 *
 * @author: liujunbo
 * @since: 2018/8/16
 * @version: $Revision$
 */
@Data
public class WxConfigure {

  private String appId;

  private String appKey;

  private String mchtId;

  private WxPayConfigure payConfigure;

  private WxUrlConfigure urlConfigure;
  @Data
  public  class BasePay{
    private String payTitle;

    private String payBody;
  }
  @Data
  public class WxPayConfigure extends BasePay{

    private String payAppId;

    private String payAppKey;

    private String payMchtId;

    private String notifyUrl;
  }

  public class WxUrlConfigure{
    private static final String APPID = "{appid}";
    private static final String SECRET = "{secret}";
    private static final String ACCESS_TOKEN = "{access_token}";
    private static final String OPENID = "{openid}";
    private static final String CODE = "{CODE}";
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


    //统一下单url
    private String unifiedOrderUrl;

    public String getAccessTokenUrl(String appid, String secret) {
      return accessTokenUrl.replace(APPID,appid).replace(SECRET,secret);
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
      this.accessTokenUrl = accessTokenUrl;
    }

    public String getUserListUrl(String accessToken) {
      return userListUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public void setUserListUrl(String userListUrl) {
      this.userListUrl = userListUrl;
    }

    public String getJsapiUrl() {
      return jsapiUrl;
    }

    public void setJsapiUrl(String jsapiUrl) {
      this.jsapiUrl = jsapiUrl;
    }

    public String getUserInfoUrl(String accessToken,String openId) {
      return userInfoUrl.replace(ACCESS_TOKEN,accessToken).replace(OPENID,openId);
    }

    public void setUserInfoUrl(String userInfoUrl) {
      this.userInfoUrl = userInfoUrl;
    }

    public String getOpenIdUrl(String appId,String secret,String code) {
      return openIdUrl.replace(APPID,appId).replace(SECRET,secret).replace(CODE,code);
    }

    public void setOpenIdUrl(String openIdUrl) {
      this.openIdUrl = openIdUrl;
    }

    public String getCreateMenu(String accessToken) {
      return createMenu.replace(ACCESS_TOKEN,accessToken);
    }

    public void setCreateMenu(String createMenu) {
      this.createMenu = createMenu;
    }

    public String getTemplateListUrl(String accessToken) {
      return templateListUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public void setTemplateListUrl(String templateListUrl) {
      this.templateListUrl = templateListUrl;
    }

    public String getSendTemplateUrl(String accessToken){
      return sendTemplateUrl.replace(ACCESS_TOKEN,accessToken);
    }

    public void setSendTemplateUrl(String sendTemplateUrl) {
      this.sendTemplateUrl = sendTemplateUrl;
    }

    public String getMenuInfoUrl(String accessToken) {
      return menuInfoUrl.replace(ACCESS_TOKEN,accessToken);
    }


    public void setMenuInfoUrl(String menuInfoUrl) {
      this.menuInfoUrl = menuInfoUrl;
    }

    public String getUnifiedOrderUrl() {
      return unifiedOrderUrl;
    }

    public void setUnifiedOrderUrl(String unifiedOrderUrl) {
      this.unifiedOrderUrl = unifiedOrderUrl;
    }
  }
}
