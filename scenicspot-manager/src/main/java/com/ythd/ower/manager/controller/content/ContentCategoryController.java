package com.ythd.ower.manager.controller.content;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.FtpUtil;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.content.ContentCategoryService;
import com.ythd.ower.server.service.content.ContentService;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sys/content/category")
public class ContentCategoryController extends BaseController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	
	@Autowired
	private ContentService contentService;

	/**
	 * 列表
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = { "list", "" })
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		pd.put("DISABLED", 1);
		List<PageData> varList = contentCategoryService.getByMap(pd);
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.setViewName("content/category_list");
		return mv;
	}
	/**
	 * 编辑页面
	 */
	@RequestMapping(value = "edit")
	public ModelAndView edit() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("categoryList",contentCategoryService.getByMap(new PageData()));
		mv.addObject("dataModel", contentCategoryService.getById(EAString.stringToInt(pd.getAsString("CATEGORY_ID"), 0)));
		mv.setViewName("content/category_edit");
		return mv;
	}

	@RequestMapping(value = "editAction")
	public ModelAndView editAction(@RequestParam(required=false,value = "CATEGORY_ICON") MultipartFile file,HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		PageData pd = getPageData(multipartRequest);
		try {
			pd.put("DISABLED", 1);
			if(StringUtils.isNotEmpty(pd.getAsString("CATEGORY_ID"))){
				//编辑
				if (file!= null && !file.isEmpty()) {
					String savePath = FtpUtil.upload(file, "wzd", "content");
					pd.put("CATEGORY_ICON", savePath);
				}
				contentCategoryService.edit(pd);
			}else{
				String savePath = FtpUtil.upload(file, "wzd", "content");
				pd.put("CATEGORY_ICON", savePath);
				//生成编码
				pd.put("CATEGORY_CODE", genCategoryCode());
				contentCategoryService.create(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/sys/content/category/list");
		return mv;
	}

	private String genCategoryCode(){
		PageData param = new PageData();
		List<PageData> categorys = null;
		String categoryCode = "";
		do {
			categoryCode = EAString.getRandomString(5);
			param.put("CATEGORY_CODE", categoryCode);
			categorys = contentCategoryService.getByMap(param);
		} while (categorys != null && categorys.size() > 0);
		return categoryCode;
	}
	
	/**
	 * 删除栏目
	 * 检查栏目下面是否有文章  
	 * 
	 */
	@RequestMapping("del")
	public void del(HttpServletResponse response){
		try{
			PageData pd = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getAsString("CATEGORY_CODE"))){
				//根据code查询内容
				List<PageData> contents = contentService.getByMap(pd);
				if(contents != null &&contents.size() > 0){
					super.outJson(response, REQUEST_FAILS, "请先删除该类目下面的文章", null);
					return;
				}
				pd.put("DISABLED", 0);
				contentCategoryService.edit(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "查询成功", null);
		}catch(Exception e){
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, "系统错误", null);
		}
	}
}
