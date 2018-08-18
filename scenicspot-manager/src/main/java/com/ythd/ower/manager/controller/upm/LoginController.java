package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.entity.upm.Admin;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.upm.AdminService;
import com.ythd.ower.server.upm.Jurisdiction;
import com.ythd.ower.server.upm.contants.Const;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main/")
public class LoginController  extends BaseController {

	@Resource(name="adminService")
	public AdminService adminService;
	
	//@Resource(name="courseService")
	//private CourseService courseService;
	
	
	//@Resource(name="schoolService")
	//private SchoolService schoolService;
	

	
	@RequestMapping("index.do")
	public ModelAndView index(){
		ModelAndView mv = this.getModelAndView();
		PageData param = this.getPageData();
		Session session = Jurisdiction.getSession();
		Admin user = (Admin)session.getAttribute(Const.SESSION_USER);
		adminService.index(param,mv);
		mv.setViewName("index/index");
		return mv;
	}
	/**
	 * 登录页面
	 */
	@RequestMapping("login.do")
	public ModelAndView login(){
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login");
		mv.addObject("system_name", "秦汉影视城后台管理系统");
		return mv;
	}
	
	@RequestMapping("doLogin.do")
	public void  doLogin(HttpServletResponse response){
		PageData pd = this.getPageData();
		try {
			adminService.doLogin(pd);
			super.outJson(response, REQUEST_SUCCESS,"登录成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
	} 
	/**
	 * 进入首页后的默认页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/default.do")
	public ModelAndView defaultPage() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData param = new PageData();
		Session session = Jurisdiction.getSession();
		Admin user = (Admin)session.getAttribute(Const.SESSION_USER);
		param.put("USERID", user.getUSER_ID());
		mv.setViewName("index/default");
		return mv;
	}
	/**
	 * 用户注销
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/logout.do")
	public ModelAndView logout() throws Exception{
		String USERNAME = Jurisdiction.getUsername();	//当前登录的用户名
		logBefore(logger, USERNAME+"退出系统");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Session session = Jurisdiction.getSession();	//以下清除session缓存
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(USERNAME + Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(USERNAME + Const.SESSION_allmenuList);
		session.removeAttribute(USERNAME + Const.SESSION_menuList);
		session.removeAttribute(USERNAME + Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERROL);
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		pd = this.getPageData();
		pd.put("msg", pd.getString("msg"));
		mv.setViewName("login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	
}
