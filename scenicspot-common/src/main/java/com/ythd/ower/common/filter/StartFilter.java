package com.ythd.ower.common.filter;

import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.dto.AppClientConfig;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * Description:设置全局参数
 * @author: liujunbo
 * @since: 2018/8/22
 * @version: $Revision$
 */
public class StartFilter implements Filter{

  private static final String CONTEXTCONFIG = "SystemConfiure";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws ServletException,IOException{
    System.out.println(servletRequest.getAttribute("currentPage"));
    Object configObj = servletRequest.getServletContext().getAttribute(CONTEXTCONFIG);
    if(configObj == null){
      AppClientConfig appClientConfig = AppClientConfig.builder().setDomain(ConfigureManager.getSystemConfig().getDomain())
              .setPageSize(ConfigureManager.getAppConfig().getPageSize() + StringUtils.EMPTY)
              .setShowUrl(ConfigureManager.getSystemConfig().getFtpConfigure().getImageShowPath())
              .setSystemName(ConfigureManager.getSystemConfig().getSystemName())
              .setCartLimit(ConfigureManager.getAppConfig().getPuroductConfig().getCartLimit() + StringUtils.EMPTY)
              .setBuyCountLimit(ConfigureManager.getAppConfig().getPuroductConfig().getBuyCountLimit() + StringUtils.EMPTY)
              .setSingleProductBuyLimit(ConfigureManager.getAppConfig().getPuroductConfig().getSingleProductBuyLimit() + StringUtils.EMPTY);
      servletRequest.getServletContext().setAttribute(CONTEXTCONFIG,appClientConfig);
    }
    filterChain.doFilter(servletRequest,servletResponse);
  }

  @Override
  public void destroy() {
  }
}
