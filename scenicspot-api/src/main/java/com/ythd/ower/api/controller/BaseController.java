package com.ythd.ower.api.controller;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.properties.PropertiesHelper;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.Tools;
import com.ythd.ower.wx.common.WechatUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The class  ${CLASSNAME}.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/9/7
 * @version: $Revision$
 */
public abstract class BaseController {

  public HttpServletRequest getRequest() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request;
  }

  public PageData getPageData(){
    PageData pd=new PageData(this.getRequest());
    Tools.replaceEmpty(pd);
    return pd;
  }

  protected Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
    Object objSessionAttribute = null;
    HttpSession session = request.getSession(false);
    if (session != null) {
      objSessionAttribute = session.getAttribute(sessionKey);
    }
    return objSessionAttribute;
  }


}
