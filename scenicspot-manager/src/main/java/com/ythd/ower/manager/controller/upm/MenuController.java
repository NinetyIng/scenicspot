package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.data.entity.upm.Menu;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.upm.MenuService;
import com.ythd.ower.server.upm.Jurisdiction;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONArray;

/**
 * 系统菜单管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/menu/")
public class MenuController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ButtonRightController.class);

	@Resource(name="menuService")
	private MenuService menuService;

	String menuUrl = "/menu/main.do";

	/**
	 * 显示菜单列表
	 * @return
	 */
	@RequestMapping("list.do")
	public ModelAndView list()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		try{
			String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString()))?"0":pd.get("MENU_ID").toString();
			List<Menu> menuList = menuService.listSubMenuByParentId(MENU_ID);
			mv.addObject("pd", menuService.getMenuById(pd));
			mv.addObject("MENU_ID", MENU_ID);
			mv.addObject("MSG", null == pd.get("MSG")?"list":pd.get("MSG").toString());
			mv.addObject("menuList", menuList);
			mv.addObject("QX", Jurisdiction.getHC());
			mv.setViewName("system/menu/menu_list");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

	@RequestMapping("main.do")
	public ModelAndView main() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = this.getModelAndView();
		String json = StringUtils.EMPTY;
		JSONArray arr = JSONArray.fromObject(menuService.listAllMenu("0"));
		json = arr.toString().replaceAll("target", "tags").replaceAll("MENU_NAME", "text").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("MENU_URL", "href");;
		json = json.replace(",\"nodes\":[]", "");
		mv.setViewName("system/menu/menu_btree");
		mv.addObject("menuJson", json);
		return mv;
	}
	
	@RequestMapping("menuJson.do")
	public void menuJson(HttpServletResponse response) throws Exception{
		JSONArray arr = JSONArray.fromObject(menuService.listAllMenu("0"));
		String json = arr.toString().replaceAll("target", "tags").replaceAll("MENU_NAME", "text").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked").replaceAll("MENU_URL", "href");;
		json = json.replace(",\"nodes\":[]", "");
		super.outJson(response, REQUEST_SUCCESS, "查询成功", json);
	}
	/**
	 * 请求编辑菜单页面
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/toEdit.do")
	public ModelAndView toEdit(String id)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = null;
		try{
			pd = this.getPageData();
			pd.put("MENU_ID",id);
			pd = menuService.getMenuById(pd);
			mv.addObject("pd", pd);
			pd.put("MENU_ID",pd.get("PARENT_ID").toString());
			mv.addObject("pds", menuService.getMenuById(pd));
			mv.addObject("MENU_ID", pd.get("PARENT_ID").toString());
			mv.addObject("MSG", "edit.do");
			pd.put("MENU_ID",id);
			mv.addObject("QX",Jurisdiction.getHC());
			mv.setViewName("system/menu/menu_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	@RequestMapping(value="/edit.do")
	public void edit(Menu menu,HttpServletResponse response)throws Exception{
		LOGGER.info("{},开始编辑菜单",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){
			LOGGER.error("{},无编辑菜单的权限",Jurisdiction.getUsername());
			return;
		}
		try{
			menuService.edit(menu);
			LOGGER.error("{},编辑菜单成功",Jurisdiction.getUsername());
			super.outJson(response, REQUEST_SUCCESS, "操作成功", menu);
		} catch(Exception e){
			LOGGER.error("{},编辑菜单失败，错误信息{}",Jurisdiction.getUsername(), e);
			super.outJson(response, REQUEST_FAILS, "操作失败", null);
		}
	}
	/**
	 * 请求新增菜单页面
	 * @return
	 */
	@RequestMapping(value="/toAdd.do")
	public ModelAndView toAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			PageData pd = this.getPageData();
			String MENU_ID = (null == pd.get("MENU_ID") || "".equals(pd.get("MENU_ID").toString()))?"0":pd.get("MENU_ID").toString();
			pd.put("MENU_ID",MENU_ID);
			mv.addObject("pds", menuService.getMenuById(pd));
			mv.addObject("MENU_ID", MENU_ID);
			mv.addObject("MSG", "add.do");
			mv.setViewName("system/menu/menu_edit");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add.do")
	public void add(Menu menu,HttpServletResponse response)throws Exception{
		LOGGER.info("{},开始增加菜单",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){
			LOGGER.error("{},无增加菜单的权限",Jurisdiction.getUsername());
			return;
		}
		PageData pd = this.getPageData();
		try{
			menu.setMENU_ID(String.valueOf(Integer.parseInt(menuService.findMaxId(pd).get("MID").toString())+1));
			menu.setMENU_ICON("menu-icon fa fa-leaf black");
			menuService.saveMenu(menu);
			LOGGER.error("{}，增加菜单成功",Jurisdiction.getUsername());
			super.outJson(response, REQUEST_SUCCESS, "操作成功", menu);
		} catch(Exception e){
			LOGGER.error("{}，增加菜单失败，失败信息{}",Jurisdiction.getUsername(), e);
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
	}
	
	
	/**
	 * 删除菜单
	 * @param MENU_ID
	 */
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public void delete(@RequestParam String MENU_ID,HttpServletResponse response)throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){
			LOGGER.error("{},无删除菜单的权限",Jurisdiction.getUsername());
			return;
		} //校验权限
		LOGGER.info("{},进入删除菜单",Jurisdiction.getUsername());
		try{
			if(menuService.listSubMenuByParentId(MENU_ID).size() > 0){
				super.outJson(response, REQUEST_FAILS, "请先删除子菜单", null);
			}else{
				menuService.deleteMenuById(MENU_ID);
			}
		} catch(Exception e){
			LOGGER.error("{},删除菜单失败，失败信息{}",Jurisdiction.getUsername(), e);
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
		super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
	}

}
