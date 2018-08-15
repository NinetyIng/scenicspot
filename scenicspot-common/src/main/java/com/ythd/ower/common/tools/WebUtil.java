package com.ythd.ower.common.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ythd.ower.common.dto.PageData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 字符串常用方法，建议里面的方法写成静态的
 */
public class WebUtil {

	/**
	 * JS输出含有\n的特殊处理
	 * 
	 * @param pStr
	 * @return
	 */
	public static String replace4JsOutput(String pStr) {
		pStr = pStr.replace("\r\n", "<br/>&nbsp;&nbsp;");
		pStr = pStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		pStr = pStr.replace(" ", "&nbsp;");
		return pStr;
	}

	/**
	 * 在客户端浏览器中等侍t秒后，跳转至url；返回JS代码
	 * 
	 * @param msg
	 *            String 等待时的提示信息
	 * @param t
	 *            int 等待秒数
	 * @param url
	 *            String
	 * @return String
	 */
	public static String waitJump(String where, String msg, int t, String url) {
		String str = "";
		String spanid = "id" + System.currentTimeMillis();
		str = "\n<ol><b><span id=" + spanid + "> 3 </span>";
		str += "秒钟后系统将自动跳转到" + where + "... </b></ol>";
		str += "<ol>" + msg + "</ol>";
		str += "<script language=javascript>\n";
		str += "<!--\n";
		str += "function tickout(secs) {\n";
		str += spanid + ".innerText = secs;\n";
		str += "if (--secs > 0) {\n";
		str += "  setTimeout('tickout(' +secs + ')', 1000);\n";
		str += "}\n";
		str += "}\n";
		str += "tickout(" + t + ");\n";
		str += "-->\n";
		str += "</script>\n";
		str += "<meta http-equiv=refresh content=" + t + ";url=" + url + ">\n";
		return str;
	}

	/**
	 * 对字符串进行URLEncoder.encode编码
	 * 
	 * @param str
	 *            String
	 * @param charset
	 *            String
	 * @return String
	 */
	public static String UrlEncode(String str, String charset) {
		if (str == null)
			return "";

		String s = str;

		try {
			s = URLEncoder.encode(str, charset);
		} catch (Exception e) {

		}
		return s;
	}

	/**
	 * 对字符串进行utf-8编码的URLEncoder.encode
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String UrlEncode(String str) {
		return UrlEncode(str, "utf-8");
	}

	/**
	 * 对字符串进行URLDecoder.decode解码
	 * 
	 * @param str
	 *            String
	 * @param charset
	 *            String
	 * @return String
	 */
	public static String UrlDecode(String str, String charset) {
		if (str == null)
			return "";

		String s = str;

		try {
			s = URLDecoder.decode(str, charset);
		} catch (Exception e) {

		}
		return s;
	}

	/**
	 * 对字符串进行utf-8解码的URLDecoder.decode
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String UrlDecode(String str) {
		return UrlDecode(str, "utf-8");
	}

	/**
	 * 判断给定id字符串是否为大于1的数字，不是则返回0
	 * 
	 * @return
	 */
	public static int getIdIfIsId(Object obj) {
		int id = 0;
		if (obj == null || (obj != null && obj.equals("")))
			return id;
		String str = obj.toString();
		if (EAString.isInt(str))
			id = Integer.parseInt(str);
		if (id < 0)
			id = 0;
		return id;
	}

	/**
	 * 解析AJAX传递的中文字符
	 * 
	 * @param obj
	 * @return
	 */
	public static String ajaxDecode(String obj) {
		String str = null;
		try {
			str = URLDecoder.decode(obj, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 在客户端浏览器中先alert，然后重定向至toUrl；返回JS代码字符串
	 * 
	 * @param msg
	 *            String
	 * @param toUrl
	 *            String
	 * @return String
	 */
	public static String alertRedirect(String msg, String toUrl) {
		String str = "";
		str = "<script language=javascript>\n";
		str += "<!--\n";
		str += "alert(\"" + msg + "\")\n";
		if (!toUrl.equals(""))
			str += "location.href=\"" + toUrl + "\"\n";
		str += "-->\n";
		str += "</script>\n";
		return str;
	}

	/**
	 * 在客户端浏览器重定向至toUrl；返回JS代码字符串
	 * 
	 *            String
	 * @param toUrl
	 *            String
	 * @return String
	 */
	public static String redirect(String toUrl) {
		String str = "";
		str = "<script language=javascript>\n";
		str += "<!--\n";
		if (!toUrl.equals(""))
			str += "location.href=\"" + toUrl + "\"\n";
		str += "-->\n";
		str += "</script>\n";
		return str;
	}
	
	/**
	 * 替换&lt; &gt;代码
	 * @param inputString
	 * @return
	 */
	public static String transform(String inputString){
		String htmlStr = inputString;
		try {
			htmlStr = htmlStr.replaceAll("&lt;","<");
			htmlStr = htmlStr.replaceAll("&gt;",">");
		}catch(Exception e){
			
		}
		return htmlStr;
	}
	
	/**
	 * 将字符串转为HTML格式
	 * @param inputString
	 * @return
	 */
	public static String strToHtml(String inputString){
		String htmlStr = inputString;
		try {
			htmlStr = htmlStr.replaceAll("\r\n", "<br/>");
		}catch(Exception e){
			
		}
		return htmlStr;
	}
	
	/**
	 * 清除javascript代码
	 * @param inputString
	 * @return
	 */
	public static String clearScript(String inputString){
		if(inputString==null){
			return "";
		}
		String htmlStr = inputString;
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
		}catch(Exception e){
			
		}
		return htmlStr;
	}
	/**
	 * 过滤掉字符串中的html标签
	 * 
	 * @param inputString
	 * @return
	 */
	public static String HtmlToText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			htmlStr = htmlStr.replaceAll("&nbsp;", "");
			htmlStr = htmlStr.replaceAll("&quot;", "");
			htmlStr = htmlStr.replaceAll("&#039;", "");
			htmlStr = htmlStr.replaceAll("&amp;", "");
			htmlStr = htmlStr.replaceAll("&ldquo;", "");
			htmlStr = htmlStr.replaceAll("&mdash;", "");
			htmlStr = htmlStr.replaceAll("&rdquo;", "");
			textStr = htmlStr;

		} catch (Exception e) {

		}

		return textStr;// 返回文本字符串
	}

	

	/**
	 * ajax输出json
	 * 
	 * @param response
	 * @param json
	 */
	public static void outJsonString(HttpServletResponse response, String json) {
		response.setHeader("Cache-Control",
				"no-store, max-age=0, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		try {
			PrintWriter out = response.getWriter();
			out.write(json);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将不含日期时间格式的Java对象系列化为Json资料格式
	 * 
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject) {
		String jsonString = "[]";
		if (EAUtil.isEmpty(pObject)) {
			// log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检�?");
		} else {
			if (pObject instanceof ArrayList) {
				JSONArray jsonArray = JSONArray.fromObject(pObject);
				jsonString = jsonArray.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(pObject);
				jsonString = jsonObject.toString();
			}
		}
		return jsonString;
	}
	
	
	
	/**
	 * 判断是否是IE浏览器
	 * 
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isIe = true;
		int index = userAgent.indexOf("msie");
		if (index == -1) {
			isIe = false;
		}
		return isIe;
	}

	/**
	 * 判断是否是Chrome浏览器
	 * 
	 * @return
	 */
	public static boolean isChrome(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isChrome = true;
		int index = userAgent.indexOf("chrome");
		if (index == -1) {
			isChrome = false;
		}
		return isChrome;
	}

	/**
	 * 判断是否是Firefox浏览器
	 * 
	 * @return
	 */
	public static boolean isFirefox(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isFirefox = true;
		int index = userAgent.indexOf("firefox");
		if (index == -1) {
			isFirefox = false;
		}
		return isFirefox;
	}

	/**
	 * 获取客户端类型
	 * 
	 * @return
	 */
	public static String getClientExplorerType(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String explorer = "非主流浏览器";
		if (isIE(request)) {
			int index = userAgent.indexOf("msie");
			explorer = userAgent.substring(index, index + 8);
		} else if (isChrome(request)) {
			int index = userAgent.indexOf("chrome");
			explorer = userAgent.substring(index, index + 12);
		} else if (isFirefox(request)) {
			int index = userAgent.indexOf("firefox");
			explorer = userAgent.substring(index, index + 11);
		}
		return explorer.toUpperCase();
	}
	
	
	public static String getFileName(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		if (str.indexOf("/") > -1) {
			return str.substring(str.lastIndexOf("/") + 1, str.length());
		}
		return "";
	}
	
	public static String getBigFileName(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		if (str.lastIndexOf("/") > -1) {
			String big = str.substring(0,str.lastIndexOf("/") + 1)
			+str.substring(str.lastIndexOf("/") + 1,str.lastIndexOf("."))+"_b."+
			 str.substring(str.lastIndexOf(".") + 1,str.length());
			return big;
		}
		return "";
	}
	
	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @return
	 */
	public static Object getSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	/**
	 * 设置一个Session属性对象
	 * 
	 * @param request
	 * @return
	 */
	public static void setSessionAttribute(HttpServletRequest request,
			String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	/**
	 * 移除Session对象属性值
	 * 
	 * @param request
	 * @return
	 */
	public static void removeSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}

	/**
	 * 获取全局参数值
	 * 
	 * @param pParamKey
	 *            参数键名
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getParamValue(String pParamKey,HttpServletRequest request) {
		String paramValue = "";
		ServletContext context = request.getSession().getServletContext();
		if (EAUtil.isEmpty(context)) {
			return "";
		}
		List paramList = (List) context.getAttribute("EAPARAMLIST");
		for (int i = 0; i < paramList.size(); i++) {
			PageData paramDto = (PageData) paramList.get(i);
			if (pParamKey.equals(paramDto.getAsString("paramkey"))) {
				paramValue = paramDto.getAsString("paramvalue");
			}
		}
		return paramValue;
	}

	/**
	 * 获取全局参数
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getParamList(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		if (EAUtil.isEmpty(context)) {
			return new ArrayList();
		}
		return (List) context.getAttribute("EAPARAMLIST");
	}

	/**
	 * 获取指定Cookie的值
	 * 
	 * @param cookies
	 *            cookie集合
	 * @param cookieName
	 *            cookie名字
	 * @param defaultValue
	 *            缺省值
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName,
			String defaultValue) {
		if (cookies == null) {
			return defaultValue;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		return defaultValue;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public static String getValue(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		PageData pd = new PageData(request);
		
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
		}
		return name;
	}
	/**获取客户端ip
	 * @return
	 */
	public static String getIp() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }



	/**
	 * 将请求参数封装为Dto
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static PageData getParamAsPageData(HttpServletRequest request) {
		PageData dto = new PageData();
		Map map = request.getParameterMap();
		Iterator keyIterator = (Iterator) map.keySet().iterator();
		try {
			while (keyIterator.hasNext()) {
				String key = (String) keyIterator.next();
				String value = ((String[]) (map.get(key)))[0];
				/** 对加密过的值进行解密
				 if (key.startsWith("zc_")) {
				 if (StringUtils.isNotEmpty(value)) {
				 value = SecurityHelper.decrypt(value);
				 }
				 }**/
				dto.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	/**
	 * 将map转换成url
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}


	/**
	 * 将map转换成url
	 * @param map
	 * @return
	 */
	public static String formatUrlByParamsMap(String url,Map<String, Object> map) {
		if (EAString.isNullStr(url)|| EAUtil.isEmpty(map)) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		String[] arrSplit=url.split("[?]");
		if(arrSplit.length>1){
			return url+"&"+s;
		}else{
			return url+"?"+s;
		}
	}

	public static  String getIpAddr(HttpServletRequest request)  {
		String ip  =  request.getHeader( " x-forwarded-for " );
		if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
			ip  =  request.getHeader( " Proxy-Client-IP " );
		}
		if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
			ip  =  request.getHeader( " WL-Proxy-Client-IP " );
		}
		if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
			ip  =  request.getRemoteAddr();
		}
		return  ip;
	}

}