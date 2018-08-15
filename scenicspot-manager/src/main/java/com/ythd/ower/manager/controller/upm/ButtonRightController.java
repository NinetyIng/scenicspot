package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.entity.upm.Role;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.upm.ButtonRightService;
import com.ythd.ower.server.service.upm.ButtonService;
import com.ythd.ower.server.service.upm.RoleService;
import com.ythd.ower.server.upm.Jurisdiction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 按钮权限管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/buttonrights/")
public class ButtonRightController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ButtonRightController.class);

	String menuUrl = "buttonrights/list";

	@Resource(name="buttonRightService")
	private ButtonRightService buttonrightService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="buttonService")
	private ButtonService buttonService;
	
	/**列表
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception{
		LOGGER.info("列表Buttonrights，登录者{}",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
			LOGGER.error("{},无Buttonrights列表的权限",Jurisdiction.getUsername());
			return null;
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		//默认列出第一组角色(初始设计系统用户和会员组不能删除)
		if(StringUtils.isEmpty(pd.getAsString("ROLE_ID"))){
			pd.put("ROLE_ID", "1");
		}
		PageData fpd = new PageData();
		fpd.put("ROLE_ID", "0");
		//取得点击的角色组(横排的)
		PageData  currentRole = roleService.findObjectById(pd);
		//列出组(页面横向排列的一级组)
		List<Role> roleList = roleService.listAllRolesByPId(fpd);
		//列出此组下架角色
		List<Role> roleList_z = roleService.listAllRolesByPId(pd);
		//列出所有按钮
		List<PageData> buttonlist = buttonService.listAll(pd);
		//列出所有角色按钮关联数据
		List<PageData> roleFhbuttonlist = new ArrayList<>(4);
		for (Role role : roleList_z){
			pd.put("ROLE_ID",role.getROLE_ID());
			roleFhbuttonlist.addAll(buttonrightService.listAll(pd));
		}
		mv.addObject("pd", currentRole);
		mv.addObject("roleList", roleList);
		mv.addObject("roleList_z", roleList_z);
		mv.addObject("buttonlist", buttonlist);
		mv.addObject("roleFhbuttonlist", roleFhbuttonlist);
		//按钮权限
		mv.addObject("QX",Jurisdiction.getHC());
		mv.setViewName("system/buttonright/buttonrights_list");
		return mv;
	}
	
	/**点击按钮处理关联表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upRb")
	@ResponseBody
	public void updateRolebuttonrightd(HttpServletResponse response)throws Exception{
		LOGGER.info("分配按钮权限，登录者{}",Jurisdiction.getUsername());
		//校验权限
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			LOGGER.error("{},无分配按钮权限的权限",Jurisdiction.getUsername());
			return;
		}
		PageData pd = this.getPageData();
		try{
			//判断关联表是否有数据 是:删除/否:新增
			if(null != buttonrightService.findById(pd)){
				buttonrightService.delete(pd);
			}else{
				pd.put("RB_ID", this.get32UUID());
				buttonrightService.save(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "请求成功", null);
			LOGGER.info("{}分配按钮权限成功",Jurisdiction.getUsername());
		}catch(Exception e){
			LOGGER.error("{}分配按钮权限失败，失败信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
}
