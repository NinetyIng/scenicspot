package com.ythd.ower.common.dto;

/**
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/8/22
 * @version: $Revision$
 */
public class AppClientConfig {
  /**
   * 客户端域名
   */
  private String domain;
  /**
   * 图片展示的url
   */
  private String showUrl;

  /**
   * 分页大小
   */
  private String pageSize;

  /**
   * 系统名字
   */
  private String systemName;

  public static AppClientConfig builder(){
    return new AppClientConfig();
  }

  public String getDomain() {
    return domain;
  }

  public AppClientConfig setDomain(String domain) {
    this.domain = domain;
    return this;
  }

  public String getShowUrl() {
    return showUrl;
  }

  public AppClientConfig setShowUrl(String showUrl) {
    this.showUrl = showUrl;
    return this;
  }

  public String getPageSize() {
    return pageSize;
  }

  public AppClientConfig setPageSize(String pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public String getSystemName() {
    return systemName;
  }

  public AppClientConfig setSystemName(String systemName) {
    this.systemName = systemName;
    return this;
  }
}
