package com.ythd.ower.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.dto.ErrorCode;
import com.ythd.ower.common.exception.BizServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 配置管理 服务启动之后加载 加载数据库配置
 * @author: liujunbo
 * @since: 2018/8/16
 * @version: $Revision$
 */
public class ConfigureManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureManager.class);
  private static WxConfigure wxConfigure;

  private static SystemConfigure systemConfigure;

  private static AppConfigure appConfigure;


  public static WxConfigure getWxConfig(){
    if(wxConfigure != null){
      return wxConfigure;
    }
   throw new BizServiceException(ErrorCodesContants.WX_CONFIG_ERROR);
  }

  public static SystemConfigure getSystemConfig(){
    if(systemConfigure != null){
      return systemConfigure;
    }
    throw new BizServiceException(ErrorCodesContants.SYSTEM_CONFIG_ERROR);
  }
  public static AppConfigure getAppConfig(){
    if(appConfigure != null){
      return appConfigure;
    }
    throw new BizServiceException(ErrorCodesContants.APP_CONFIG_ERROR);
  }



  public static void parseWxConfig(String wxConfigJson){
    if(wxConfigure == null){
      wxConfigure = new WxConfigure();
      JSONObject wxObject = JSON.parseObject(wxConfigJson);
      JSONObject baseConfig = wxObject.getJSONObject("base");
      wxConfigure.setAppId(baseConfig.getString("appId"));
      wxConfigure.setAppKey(baseConfig.getString("appKey"));
      wxConfigure.setMchtId(baseConfig.getString("mchtId"));
      JSONObject payConfig = wxObject.getJSONObject("payConfig");
      wxConfigure.builderWxPayConfigureInstance()
              .setPayAppId(payConfig.getString("payAppId"))
              .setPayAppKey(payConfig.getString("payAppKey"))
              .setNotifyUrl(payConfig.getString("notifyUrl"))
              .setPayMchtId(payConfig.getString("payMchtId"))
              .setPayTitle(payConfig.getString("payTitle"));
      JSONObject urlConfig = wxObject.getJSONObject("urlConfig");
      wxConfigure.builderWxUrlConfigureInstance()
              .setAccessTokenUrl(urlConfig.getString("accessTokenUrl"))
              .setCreateMenu(urlConfig.getString("createMenuUrl"))
              .setJsapiUrl(urlConfig.getString("jsapiUrl"))
              .setMenuInfoUrl(urlConfig.getString("menuInfoUrl"))
              .setOpenIdUrl(urlConfig.getString("openIdUrl"))
              .setSendTemplateUrl(urlConfig.getString("sendTemplateUrl"))
              .setTemplateListUrl(urlConfig.getString("templateListUrl"))
              .setTgqrcodeUrl(urlConfig.getString("tgqrcodeUrl"))
              .setUnifiedOrderUrl(urlConfig.getString("unifiedOrderUrl"))
              .setUserInfoUrl(urlConfig.getString("userInfoUrl"))
              .setUserListUrl(urlConfig.getString("userListUrl"));
    }
  }

  public static void parseSystemConfig(String systemConfig){
    if(systemConfigure == null){
      systemConfigure = new SystemConfigure();
      JSONObject sysObject = JSON.parseObject(systemConfig);
      systemConfigure.setSystemName(sysObject.getString("systemName"));
      systemConfigure.setDomain(sysObject.getString("domain"));
      JSONObject ftpConfig = sysObject.getJSONObject("ftpConfig");
      systemConfigure.buliderFtp().setIp(ftpConfig.getString("ip"))
              .setImageShowPath(ftpConfig.getString("imageShowPath"))
              .setPassword(ftpConfig.getString("password"))
              .setUsername(ftpConfig.getString("username"));
    }
  }


  public static void parseAppConfig(String appConfig){
    if(appConfigure == null){
      appConfigure = AppConfigure.bulider();

      JSONObject appObject = JSON.parseObject(appConfig);
      JSONObject productConfigObject = appObject.getJSONObject("puroductConfig");

      appConfigure.setPageSize(appObject.getIntValue("pageSize"))
              .builderProductConfig().setCartLimit(productConfigObject.getInteger("cartLimit"))
              .setIndexLastCount(productConfigObject.getInteger("indexLastCount"))
              .setIndexRecommendCount(productConfigObject.getInteger("indexRecommendCount"));
    }
  }
}
