package com.ythd.ower.manager.controller.upm;

import com.ythd.ower.common.tools.FtpUtil;
import com.ythd.ower.manager.controller.comm.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONArray;

/**
 * 文件服务器控制器 可对图片进行删除
 * @author liujunbo
 *
 */
@Controller
@RequestMapping("/sys/storage/")
public class StorageController extends BaseController {

	@RequestMapping("list")
	public ModelAndView list() throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/storage/list");
		List<FtpUtil.FileEntity> ftpFiles = FtpUtil.recursionFileEntity("/");
		JSONArray jsonArray = JSONArray.fromObject(ftpFiles);
		String json = jsonArray.toString().replaceAll("target", "tags").replaceAll("showFileName", "text").replaceAll("fileEntitys", "nodes").replaceAll("file", "checked").replaceAll("MENU_URL", "href");;
		json = json.replace(",\"nodes\":[]", "");
		mv.addObject("fileList", json);
		return mv;
	}
	/**
	 * 删除文件
	 */
	@RequestMapping("deleteFile")
	public void deleteFilt(String pathName,HttpServletResponse response){
		try{
			FtpUtil.removeAllFiles(pathName);
			super.outJson(response, super.REQUEST_SUCCESS, "删除成功", null);
		}catch(Exception e){
			e.printStackTrace();
			super.outJson(response, super.REQUEST_FAILS, "删除失败", null);
		}
	}
}
