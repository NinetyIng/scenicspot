package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.MD5;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.data.entity.upm.Role;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.upm.AdminService;
import com.ythd.ower.server.service.upm.MenuService;
import com.ythd.ower.server.service.upm.RoleService;
import com.ythd.ower.server.upm.Jurisdiction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统管理
 * @author Administrator
 */
@RequestMapping("/user/")
@Controller
public class AdminController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	String menuUrl = "user/listUsers";
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="roleService")
	private RoleService roleService;
	/**显示用户列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page)throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
			LOGGER.error("{}，无查询系统用户的权限",Jurisdiction.getUsername());
			return null;
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		if(StringUtils.isNotEmpty(pd.getString("keywords"))){
			pd.put("keywords", pd.getString("keywords").trim());
		}
		String lastLoginStart = pd.getString("lastLoginStart");
		String lastLoginEnd = pd.getString("lastLoginEnd");
		pd.put("type", "system");
		if(StringUtils.isNotEmpty(lastLoginStart)){
			pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
		}
		if(StringUtils.isNotEmpty(lastLoginEnd) ){
			pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
		} 
		pd.put("role", pd.getAsString("ROLE_ID"));
		page.setPd(pd);
		List<PageData>	userList = adminService.listUsers(page);
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);
		mv.setViewName("system/user/user_list");
		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
	/**删除用户
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteU")
	public void deleteU(HttpServletResponse response) throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
			LOGGER.error("{}，无删除管理员权限",Jurisdiction.getUsername());
			return;
		}
		PageData pd  = this.getPageData();
		adminService.deleteU(pd);
		LOGGER.error("{}，删除管理员成功",Jurisdiction.getUsername());
		super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
	}
	/**
	 * 去新增用户页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goAddU")
	public ModelAndView goAddU()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd =  this.getPageData();
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveU")
	public void saveU(HttpServletResponse response){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			LOGGER.error("{}，无添加管理员权限",Jurisdiction.getUsername());
			return;
		}
		try{
			PageData pd = this.getPageData();
			pd.put("USER_ID", this.get32UUID());
			pd.put("LAST_LOGIN", "");
			pd.put("IP", "");
			pd.put("STATUS", "0");
			pd.put("SKIN", "default");
			pd.put("RIGHTS", "");		
			pd.put("PASSWORD", MD5.md5(pd.getAsString("PASSWORD")));
			adminService.saveU(pd);
			LOGGER.info("{}，新增管理员成功",Jurisdiction.getUsername());
			super.outJson(response, REQUEST_SUCCESS,"操作成功", pd);
		}catch(Exception e){
			LOGGER.error("{}，新增管理员失败，失败信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
	}
	/**判断用户名，邮箱，编码是否存在
	 * @return
	 */
	@RequestMapping(value="/hasExist")
	public void hasExist(HttpServletResponse response){
		PageData pd = null;
		try{	
			pd = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getAsString("t_number"))){
				pd.put("NUMBER", pd.getAsString("t_number"));
			}
			List<PageData> results = adminService.getUserByCondition(pd); 
			if( results != null && results.size() > 0 ){
				super.outJson(response, REQUEST_FAILS, "已存在", null);
			}else{
				super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
			}
		} catch(Exception e){
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
		
	}
	/**去修改用户页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditU")
	public ModelAndView goEditU() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd  = this.getPageData();
		String fx = pd.getString("fx");
		if("head".equals(fx)){
			mv.addObject("fx", "head");
		}else{
			mv.addObject("fx", "user");
		}
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);
		pd = adminService.getUserInfo(pd);
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}
	
	/**查看用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/view")
	public ModelAndView view() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);
		pd = adminService.getUserInfo(pd);
		mv.setViewName("system/user/user_view");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}
	
	/**去修改用户页面(在线管理页面打开)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goEditUfromOnline")
	public ModelAndView goEditUfromOnline() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		pd.put("ROLE_ID", "1");
		List<Role> roleList = roleService.listAllRolesByPId(pd);
		pd = adminService.getUserInfo(pd);
		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "editU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);
		return mv;
	}
	/**
	 * 修改用户
	 */
	@RequestMapping(value="/editU")
	public void editU(HttpServletResponse response){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			LOGGER.error("{}。无编辑管理员权限",Jurisdiction.getUsername());
			return;}
		try{
			PageData pd  = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getString("PASSWORD"))){
				pd.put("PASSWORD", MD5.md5(pd.getAsString("PASSWORD")));
			}
			adminService.editU(pd);
			LOGGER.info("{}，修改管理员成功，管理员信息{}",Jurisdiction.getUsername(), MapperUtil.toJson(pd));
		   super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
		}catch(Exception e){
			LOGGER.error("{}，修改管理员失败，失败信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
	}
	/**
	 * 批量删除
	 * @throws Exception 
	 */
	@RequestMapping(value="/deleteAllU")
	public void deleteAllU(HttpServletResponse response){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
			LOGGER.error("{}。无删除管理员权限",Jurisdiction.getUsername());
			return;
		}
		PageData pd = this.getPageData();
		String USER_IDS = pd.getString("USER_IDS");
		if( StringUtils.isNotEmpty(USER_IDS)){
			String ArrayUSER_IDS[] = USER_IDS.split(",");
			adminService.deleteBatch(ArrayUSER_IDS);
			LOGGER.error("{}。批量删除管理员成功",Jurisdiction.getUsername());
			super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
		}else{
			super.outJson(response, PARAM_ERROR, "参数不能为空", null);
		}
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
