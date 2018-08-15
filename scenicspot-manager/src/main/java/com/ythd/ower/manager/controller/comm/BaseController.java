package com.ythd.ower.manager.controller.comm;


import com.ythd.ower.common.dto.Page;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.common.tools.Tools;
import com.ythd.ower.common.tools.WebUtil;
import com.ythd.ower.data.entity.upm.Admin;
import com.ythd.ower.server.upm.Jurisdiction;
import com.ythd.ower.server.upm.contants.Const;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

public class BaseController {
	
	protected static final String REQUEST_SUCCESS     =  "200";
	protected static final String PARAM_ERROR         =  "201";
	protected static final String REFUSE_ACCESS       =  "202";
	protected static final String NO_DATA             =  "203";
	protected static final String TO_LOGIN            =  "204";
	protected static final String REQUEST_FAILS       =  "205";
	protected String getAdminName(){
		Session session = Jurisdiction.getSession();
		Admin admin = (Admin)session.getAttribute(Const.SESSION_USER);
		return admin.getNAME();
	}
	
	
	protected String getWxAppUserCardId(){
		return (String) getRequest().getSession().getAttribute("SFZHBACK");
	}
	
	
	/**
	 * 鎵嬫満绔瘡椤佃褰曟暟
	 */
	protected static final Integer PAGESIZE = 15;
	
	protected Logger logger = LoggerFactory.getLogger(BaseController.class);

	/** new PageData瀵硅薄
	 * @return
	 */
	public PageData getPageData(){
		PageData pd=new PageData(this.getRequest());
		Tools.replaceEmpty(pd);
		return pd;
	}
	
	/** new PageData瀵硅薄 锛�鏂囦欢涓婁紶
	 * @return
	 */
	public PageData getPageData(MultipartHttpServletRequest multipartRequest){
		return new PageData(multipartRequest);
	}
	
	/**寰楀埌ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**寰楀埌request瀵硅薄
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**寰楀埌32浣嶇殑uuid
	 * @return
	 */
	public String get32UUID(){
		return EAString.get32UUID();
	}
	
	/**寰楀埌鍒嗛〉鍒楄〃鐨勪俊鎭�
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	public void outJsonString(HttpServletResponse response, boolean result, String msg, Object data) {
		response.setHeader("Cache-Control",
				"no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		 /* 璁剧疆鏍煎紡涓簍ext/json    */
        response.setContentType("text/json"); 
        /*璁剧疆瀛楃闆嗕负'UTF-8'*/
        response.setCharacterEncoding("UTF-8"); 
		try {
			PrintWriter out = response.getWriter();
			JSONObject reParam = new JSONObject(false);
			reParam.put("result", result);
			reParam.put("data", data);
			reParam.put("msg", msg);
			out.write(reParam.toString());
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void outJson(HttpServletResponse response, String result, String msg,Object data) {
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		 /* 璁剧疆鏍煎紡涓簍ext/json    */
        response.setContentType("text/json"); 
        /*璁剧疆瀛楃闆嗕负'UTF-8'*/
        response.setCharacterEncoding("UTF-8"); 
		try {
			PrintWriter out = response.getWriter();
			JSONObject reParam = new JSONObject(false);
			reParam.put("result", result);
			reParam.put("data", data);
			reParam.put("msg", msg);
			out.write(reParam.toString());
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void outJson(HttpServletResponse response, PageData data) {
		response.setHeader("Cache-Control",
				"no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		 /* 设置格式为text/json    */
        response.setContentType("text/json"); 
        /*设置字符集为'UTF-8'*/
        response.setCharacterEncoding("UTF-8"); 
		try {
			PrintWriter out = response.getWriter();
			out.write(data.toJson());
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 杈撳嚭JSON瀛楃涓插埌瀹㈡埛绔�
	 * @param result 杩斿洖鐘舵�浠ｇ爜
	 * @param msg 杩斿洖鐨勬彁绀烘秷鎭�
	 * @param data 杩斿洖鏁版嵁涓婚
	 * @param response
	 */
	public void outJson(String result, String msg,Object data,HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		 /* 璁剧疆鏍煎紡涓簍ext/json    */
        response.setContentType("text/json"); 
        /*璁剧疆瀛楃闆嗕负'UTF-8'*/
        response.setCharacterEncoding("UTF-8"); 
		try {
			PrintWriter out = response.getWriter();
			JSONObject reParam = new JSONObject(false);
			reParam.put("code", result);
			reParam.put("data", data);
			reParam.put("msg", msg);
			out.write(reParam.toString());
			out.close();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void printAllParameter(){
		Enumeration<String> rnames=getRequest().getParameterNames();
		for (Enumeration<String> e = rnames ; e.hasMoreElements() ;) {
			String thisName=e.nextElement().toString();
		    String thisValue=getRequest().getParameter(thisName);
		    System.out.println(thisName+"-------"+thisValue);
		} 
	}
	
	
	protected String getOpenId(){
		String openId= WebUtil.getSessionAttribute(this.getRequest(),"openid")+"";
		return openId;
	}
	
	protected String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 鑾峰彇涓�釜Session灞炴�瀵硅薄
	 * 
	 * @param request
	 * @return
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}
	
}
