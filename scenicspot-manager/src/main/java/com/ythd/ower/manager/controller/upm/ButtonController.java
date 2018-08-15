package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.upm.ButtonService;
import com.ythd.ower.server.upm.Jurisdiction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/** 
 * 说明：按钮管理
 * 创建人：hsia
 * 创建时间：2016-01-15
 */
@Controller
@RequestMapping(value="/button")
public class ButtonController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ButtonRightController.class);

	String menuUrl = "button/list";
	@Resource(name="buttonService")
	private ButtonService buttonService;
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public void save(HttpServletResponse response) throws Exception{
		LOGGER.info("{},按钮保存",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return;}
		try {
			ModelAndView mv = this.getModelAndView();
			PageData pd = this.getPageData();
			pd.put("FHBUTTON_ID", this.get32UUID());
			buttonService.save(pd);
			super.outJson(response, REQUEST_SUCCESS, "请求成功", null);
		}catch (Exception e){
			LOGGER.error("{}，按钮保存出错，错误信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}
	
	/**删除
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(HttpServletResponse response) throws Exception{
		LOGGER.info("{},删除按钮",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;}
		try {
			PageData pd = this.getPageData();
			buttonService.delete(pd);
			LOGGER.info("{},删除成功",Jurisdiction.getUsername());
			super.outJson(response, REQUEST_SUCCESS, "请求成功", null);
		}catch (Exception e){
			LOGGER.error("{}，删除出错，错误信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public void edit(HttpServletResponse response) throws Exception{
		LOGGER.info("{},编辑按钮",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return;}
		try{
			PageData pd = this.getPageData();
			buttonService.edit(pd);
			LOGGER.info("{},编辑成功",Jurisdiction.getUsername());
		}catch (Exception e){
			LOGGER.error("{}，删除出错，错误信息{}",Jurisdiction.getUsername(),e);
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		LOGGER.info("{},按钮列表",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){
			LOGGER.error("{},无查询按钮列表权限",Jurisdiction.getUsername());
			return null;
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String keywords = pd.getString("keywords");
		if(StringUtils.isNotEmpty(keywords)){
			pd.put("keywords",StringUtils.trim(keywords));
		}
		page.setPd(pd);
		List<PageData>	varList = buttonService.list(page);
		mv.setViewName("system/fhbutton/fhbutton_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.setViewName("system/fhbutton/fhbutton_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		pd = buttonService.getById(pd.getAsString("FHBUTTON_ID"));
		mv.setViewName("system/fhbutton/fhbutton_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	

	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		LOGGER.info("{},导出Fhbutton到excel",Jurisdiction.getUsername());
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("名称");
		titles.add("权限标识");
		titles.add("备注");
		dataMap.put("titles", titles);
		List<PageData> varOList = buttonService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("NAME"));
			vpd.put("var2", varOList.get(i).getString("QX_NAME"));
			vpd.put("var3", varOList.get(i).getString("BZ"));
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
