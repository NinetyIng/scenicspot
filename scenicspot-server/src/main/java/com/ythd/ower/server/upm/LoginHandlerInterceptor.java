package com.ythd.ower.server.upm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ythd.ower.data.entity.upm.Admin;
import com.ythd.ower.server.upm.contants.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginHandlerInterceptor  extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else{
			Admin user = (Admin) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if(user!=null){
				path = path.substring(1, path.length());
				boolean b = Jurisdiction.hasJurisdiction("/"+path);
				if(!b){
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			}else{
				//登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;		
			}
		}
	}
}
