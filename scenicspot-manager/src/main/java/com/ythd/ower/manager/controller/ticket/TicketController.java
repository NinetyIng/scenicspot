package com.ythd.ower.manager.controller.ticket;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.server.service.ticket.TicketService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 票务系统
 * 
 * @author liujunbo
 *
 */
@Controller
@RequestMapping("/sys/ticket/")
public class TicketController  extends BaseController {
   
	
	@Autowired
	private TicketService ticketService;
	
	/**
	 * 
	 * 票类分类列表
	 */
	@RequestMapping("categoryList")
	public ModelAndView  categoryList(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.addObject("dataModel", ticketService.categorySelectByMap(pd));
		mv.setViewName("/ticket/category/list");
		return mv;
	}
	
	
	@RequestMapping("categoryEditPage")
	public ModelAndView  categoryEditPage(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		if(StringUtils.isNotEmpty(pd.getAsString("id"))){
			mv.addObject("dataModel", ticketService.categorySelectByMap(pd).get(0));
		}
		mv.setViewName("/ticket/category/edit");
		return mv;
	}
	@RequestMapping("categoryEdit")
	public void  categoryEdit(HttpServletResponse response){
		try{
			PageData pd = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getAsString("id"))){
				ticketService.categoryUpdate(pd);
			}else{
				ticketService.categoryInsert(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
		}catch(Exception e){
			super.outJson(response, REQUEST_FAILS, "操作失败", null);
		}
	}
	@RequestMapping("categoryDel")
	public void  categoryDel(HttpServletResponse response){
		try{
			PageData pd = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getAsString("id"))){
				ticketService.categoryDel(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
		}catch(Exception e){
			super.outJson(response, REQUEST_FAILS, "操作失败", null);
		}
	}
	@RequestMapping("list")
	public ModelAndView list(Page page) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = getPageData();
		page.setPd(pd);
		List<PageData> list = ticketService.getByPage(page);
		mv.addObject("dataModel", list);
		mv.addObject("pd", pd);
		mv.addObject("page", page);
		mv.setViewName("/ticket/list");
		return mv;
	}
	/**
	 * 景点删除
	 * 
	 */
	@RequestMapping("del")
	public void del(HttpServletResponse response){
		try{
			PageData pd = this.getPageData();
			ticketService.delete(pd);
			super.outJson(response, REQUEST_SUCCESS, "删除成功", null);
		}catch(Exception e){
			super.outJson(response, REQUEST_FAILS, "删除失败", null);
			e.printStackTrace();
		}
	}
	/**
	 * 票类编辑页面
	 */
	@RequestMapping("editPage")
	public ModelAndView editPage() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = getPageData();
		pd.put("scenic_is_ticket", "1");
		mv.addObject("cateList", ticketService.categorySelectByMap(pd));
		mv.addObject("dataModel", ticketService.getById(EAString.stringToInt(pd.getAsString("t_id"), 0)));
		mv.setViewName("/ticket/edit");
		return mv;
	}
	@RequestMapping("eidt")
	public void eidt(HttpServletResponse response){
		try{
			PageData pd = this.getPageData();
			if(StringUtils.isNotEmpty(pd.getAsString("id"))){
				ticketService.edit(pd);
			}else{
				pd.put("create_time", EADate.getCurrentTime());
				ticketService.create(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "操作成功",null);
		}catch(Exception e){
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, "操作失败", null);
		}
	}
}
