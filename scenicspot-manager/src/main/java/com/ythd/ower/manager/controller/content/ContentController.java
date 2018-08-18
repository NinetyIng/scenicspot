package com.ythd.ower.manager.controller.content;

import com.ythd.ower.common.constants.NumberContants;
import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EADate;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.FtpUtil;
import com.ythd.ower.common.tools.FtpUtilsAbove;
import com.ythd.ower.common.tools.Tools;
import com.ythd.ower.manager.controller.comm.BaseController;
import com.ythd.ower.manager.controller.upm.AdminController;
import com.ythd.ower.server.service.content.ContentCategoryService;
import com.ythd.ower.server.service.content.ContentService;
import com.ythd.ower.server.service.content.SubjectService;
import com.ythd.ower.server.service.content.constant.ContentConstant;
import com.ythd.ower.server.service.upm.constant.UpmConstant;
import com.ythd.ower.server.upm.Jurisdiction;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/sys/content/")
public class ContentController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

  private static final String KEYWORDS = "keywords";


	@Autowired
	private ContentService contentService;

	@Autowired
	private ContentCategoryService contentCategoryService;

	@Autowired
	private SubjectService subjectService;

	String menuUrl = "/sys/content/list";

	@RequestMapping(value = { "list.do", "" })
	public ModelAndView list(Page page) {
		if(!Jurisdiction.buttonJurisdiction(menuUrl, UpmConstant.UPM_SEL)){
      LOGGER.error("{},无查询文章权限",Jurisdiction.getUsername());
		  return null;
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd= this.getPageData();
		String keywords = pd.getString(KEYWORDS);
		if (StringUtils.isNotEmpty(keywords)) {
			pd.put(KEYWORDS, keywords.trim());
		}
		page.setPd(pd);
		List<PageData> varList = contentService.getByPage(page);
    //查询所有的栏目
		List<PageData> cateList = contentCategoryService.getByMap(new PageData());
		mv.setViewName("content/content_list");
		mv.addObject("varList", varList);
		mv.addObject(ContentConstant.COMMON_PD, pd);
		mv.addObject(ContentConstant.CATEGORY_LIST,cateList);
		mv.addObject("QX", Jurisdiction.getHC());
		return mv;
	}

	/**
	 * 跳转到文章编辑页面
	 * @return
	 */
	@RequestMapping("editPage.do")
	public ModelAndView editPage(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		PageData content = contentService.getById(EAString.stringToInt(pd.getAsString(ContentConstant.ContentFiled.CONTENT_ID), NumberContants.ZERO));
		mv.addObject(ContentConstant.COMMON_PD, content);
		mv.addObject(ContentConstant.COMMON_MSG, ContentConstant.RequestType.REQUEST_ADD);
		if(EAString.stringToInt(pd.getAsString(ContentConstant.ContentFiled.CONTENT_ID),NumberContants.ZERO) > NumberContants.ZERO){
			mv.addObject(ContentConstant.COMMON_MSG, ContentConstant.RequestType.REQUEST_EDIT);
		}
		pd.put(ContentConstant.ContentFiled.DISABLED,NumberContants.ONE);
    mv.addObject(ContentConstant.CATEGORY_LIST,contentCategoryService.getByMap(pd));
    mv.addObject(ContentConstant.SUBJECT_LIST,subjectService.getByMap(pd));
    mv.setViewName("content/content_edit");
		return mv;
	}


	/**
	 * 文章保存
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public void save(HttpServletResponse response){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, UpmConstant.UPM_ADD)){
			LOGGER.error("{},无新增文章权限",Jurisdiction.getUsername());
			return;
		}
		try {
			PageData pd = this.getPageData();
			if (StringUtils.isEmpty(pd.getAsString(ContentConstant.ContentFiled.CONTENT))) {
				pd.put(ContentConstant.ContentFiled.CONTENT, pd.getAsString("editorValue"));
			}
			pd.put(ContentConstant.ContentFiled.RECOMMEND, Boolean.parseBoolean(pd.getString(ContentConstant.ContentFiled.RECOMMEND)));
			pd.put(ContentConstant.ContentFiled.ISHOT, Boolean.parseBoolean(pd.getString(ContentConstant.ContentFiled.ISHOT)));
			pd.put(ContentConstant.ContentFiled.ISFOCUS, Boolean.parseBoolean(pd.getString(ContentConstant.ContentFiled.ISFOCUS)));
			pd.put(ContentConstant.ContentFiled.TOPLV, StringUtils.isNotEmpty(pd.getAsString(ContentConstant.ContentFiled.TOPLV)) ? pd.get(ContentConstant.ContentFiled.TOPLV) : 0);
			pd.put(ContentConstant.ContentFiled.CREATETIME, Tools.date2Str(new Date()));
			pd.put(ContentConstant.ContentFiled.CREATOR, Jurisdiction.getUsername());
			if (ContentConstant.ContentStatus.STATUS_RELEASE.equals(pd.getAsString(ContentConstant.ContentFiled.STATE))) {
				pd.put(ContentConstant.ContentFiled.PUTTIME, EADate.getCurrentTime());
			}
			if ("1".equals(pd.getAsString(ContentConstant.ContentFiled.MODEL_TYPE))) {
				List<PageData> albums = contentService.selectContentAlbums(pd.getAsInt(ContentConstant.ContentFiled.CONTENT_ID));
				if (albums == null || albums.size() != 3) {
					LOGGER.error("{},新增文章失败，失败信息:文章相册有误，当前相册照片数量为{}",Jurisdiction.getUsername(),albums.size());
					super.outJsonString(response, false, "文章相册有误，请上传3张图片！", null);
					return;
				}
				pd.put(ContentConstant.ContentFiled.T_IMG, albums.get(0).getAsString("original_img"));
			}
			contentService.create(pd);
			LOGGER.info("{},新增文章成功",Jurisdiction.getUsername());
			super.outJsonString(response, true, "操作成功", null);
		} catch (Exception e) {
			LOGGER.error("{},新增文章失败，失败信息{}",Jurisdiction.getUsername(),e);
			super.outJsonString(response, false, e.getMessage(), null);
		}
	}
	@RequestMapping("delete.do")
	public void contentDelete(HttpServletResponse response){
		PageData pd = this.getPageData();
		try{
			if(ContentConstant.ContentStatus.STATUS_DRAFT.equals(pd.getAsString(ContentConstant.ContentFiled.STATE))){
				LOGGER.info("{}，删除文章，该文章状态为草稿状态，直接被删除",Jurisdiction.getUsername());
				contentService.delete(pd);
			}else{
				LOGGER.info("{}，删除文章，该文章不是草稿状态，将被删除为草稿",Jurisdiction.getUsername());
				contentService.edit(pd);
			}
			super.outJsonString(response, Boolean.TRUE, "操作成功", null);
		}catch(Exception e){
			LOGGER.error("{}，删除文章失败，错误信息{}",Jurisdiction.getUsername(),e);
			super.outJsonString(response, Boolean.FALSE,"删除文章失败", null);
		}
	}
	/**
	 * 修改
	 * 
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit.do")
	@ResponseBody
	public void edit(HttpServletResponse response) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "修改Content");
		PageData pd = this.getPageData();
		if (StringUtils.isEmpty(pd.getAsString("CONTENT"))) {
			pd.put("CONTENT", pd.getAsString("editorValue"));
		}
		try {
			pd.put("RECOMMEND", Boolean.parseBoolean(pd.getString("RECOMMEND")));
			pd.put("DRAFT", Boolean.parseBoolean(pd.getString("DRAFT")));
			pd.put("ISHOT", Boolean.parseBoolean(pd.getString("ISHOT")));
			pd.put("ISFOCUS", Boolean.parseBoolean(pd.getString("ISFOCUS")));
			pd.put("OP_USER_ID", Jurisdiction.getUsername());
			pd.put("OP_REMARK", pd.get("OP_REMARK"));
			pd.put("TOPLV", (StringUtils.isEmpty(pd.getAsString("TOPLV"))) ? 0 : pd.get("TOPLV"));
			pd.put("MODIFIER", Jurisdiction.getUsername());
			if ("2".equalsIgnoreCase(pd.getAsString("STATE"))) {
				pd.put("PUTTIME", EADate.getCurrentTime());
			}
			if (pd.get("MODEL_TYPE") != null && "1".equals(pd.getAsString("MODEL_TYPE"))) {
				List<PageData> albums = contentService.selectContentAlbums(pd.getAsInt("CONTENT_ID"));
				if (albums == null || albums.size() != 3) {
					super.outJsonString(response, false, "文章相册有误，请上传3张图片！", null);
					return;
				}
				//同时修改列表图
				pd.put("T_IMG", albums.get(0).getAsString("original_img"));
			}
			if ("0".equalsIgnoreCase(pd.getAsString("CTYPE"))) {
				pd.put("SUBJECT_CODE", "0");
				pd.put("SUBJECT_ID", "0");
			}
			contentService.edit(pd);
			super.outJsonString(response, true, "操作成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJsonString(response, false, e.toString(), null);

		}
	}

	@RequestMapping(value = "/saveImag.do")
	public void saveImag(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(required = false) MultipartFile file,
			@RequestParam(value = "tname", required = false) String tname,
			@RequestParam(value = "CONTENT_ID", required = false) String CONTENT_ID,
			@RequestParam(value = "CATEGORY_TYPE", required = false) String CATEGORY_TYPE) throws Exception {
		logBefore(logger, Jurisdiction.getUsername() + "新增资讯图片");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		file = multipartRequest.getFile(tname);
		Map<String, String> map = new HashMap<>();
		PageData pd = this.getPageData();
		pd.put("CATEGORY_TYPE", CATEGORY_TYPE);
		String path = null;
		if (null != file && !file.isEmpty()) {
			path = FtpUtil.upload(file, "wzd", "content");
			pd.put("path", path);
			pd.put("IMGLABER", tname);
			map.put("result", "true");
		} else {
			map.put("result", "false");
		}
		map.put("path", path);
		if ("TIMG".equals(tname)) {
			tname = "T_IMG";
		} else if ("CIMG".equals(tname)) {
			tname = "C_IMG";
		}
		map.put("labname", tname);
		super.outJson(response, REQUEST_SUCCESS, "保存成功", map);
	}

	/**
	 * 删除图片
	 * 
	 * @param response
	 */
	@RequestMapping("delImg.do")
	public void deleteImg(HttpServletResponse response) {
		try {
			PageData pd = this.getPageData();
			if ("T_IMG".equals(pd.getAsString("type"))) {
				pd.put("T_IMG", "T_IMG");
			}
			if ("C_IMG".equals(pd.getAsString("type"))) {
				pd.put("C_IMG", "C_IMG");
			}
			contentService.deleteImg(pd);
			super.outJson(response, REQUEST_SUCCESS, "操作成功", null);
		} catch (Exception e) {
			super.outJson(response, REQUEST_FAILS, e.getMessage(), null);
		}
	}


	/**
	 * 跳转到相册界面
	 * @return
	 */
	@RequestMapping("resource/addPage.do")
	public ModelAndView addResourcePage() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		mv.setViewName("/content/albums_show");
		pd.put("albums", contentService.selectContentAlbums(pd.getAsInt("content_id")));
		mv.addObject("albumModel", pd);
		return mv;
	}

	/**
	 * 图片删除
	 */
	@RequestMapping("resource/delete.do")
	public void delete(HttpServletResponse response) {
		try {
			PageData pd = this.getPageData();
			if ("-2".equalsIgnoreCase(pd.getAsString("album_id"))) {
				PageData allPd = new PageData();
				allPd.put("content_id", pd.getAsString("content_id"));
				contentService.deleteContentAlbums(allPd);
			} else if (StringUtils.isNotEmpty(pd.getAsString("album_id"))) {
				contentService.deleteContentAlbums(pd);
			}
			super.outJson(response, REQUEST_SUCCESS, "请求成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}

	/**
	 * 图片上传
	 */
	@RequestMapping("resource/uploadImg.do")
	public void saveStockImg(HttpServletResponse response, HttpServletRequest request) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			PageData data = getPageData(multipartRequest);
			MultipartFile file = multipartRequest.getFile("file");
			String filePath = "";
			if (file != null) {
				/**
				 * 更新相册
				 */
				FtpUtilsAbove ftp = new FtpUtilsAbove();
				filePath = ftp.upload(file, "wzd", "images");
				data.put("original_img", filePath);
				data.put("create_time", EADate.getCurrentTime());
				contentService.insertContentAlbums(data);
			}
			super.outJson(response, REQUEST_SUCCESS, "请求成功", filePath);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, "请求失败", null);
		}
	}
	/**
	 * 拉取相册数据
	 * @param response
	 * @param request
	 */
	@RequestMapping("resource/pushContentAlbumsData.do")
	public void pushContentAlbumsData(HttpServletResponse response, HttpServletRequest request){
		try {
			PageData pd=getPageData();
			List<PageData> albumslist=contentService.selectContentAlbums(pd.getAsInt("CONTENT_ID"));
			super.outJson(response, REQUEST_SUCCESS, "查询成功", albumslist);
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, REQUEST_FAILS, "查询失败", null);
		}
	}
}
