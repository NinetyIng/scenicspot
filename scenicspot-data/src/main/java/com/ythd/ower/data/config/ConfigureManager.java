package com.ythd.ower.data.config;

/**
 * Description: 配置管理 服务启动之后加载 加载数据库配置
 *
 * @author: liujunbo
 * @since: 2018/8/16
 * @version: $Revision$
 */
public class ConfigureManager {


  public static WxConfigure getWxConfig(){
    return new WxConfigure();
  }

}
