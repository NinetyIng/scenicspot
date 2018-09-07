package com.ythd.ower.wx.common;

import com.ythd.ower.common.config.ConfigureManager;
import com.ythd.ower.common.constants.SpecificSymbolConstants;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.properties.PropertiesHelper;
import com.ythd.ower.common.tools.EAString;
import com.ythd.ower.wx.entity.Template;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public final class WechatUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(WechatUtil.class);
	/**
	 * 票据凭证字符串
	 */
	private static String ACCESSTOKEN;
	/**
	 * 最后刷新时间
	 */
	private static long lastTime;
	/**
	 * 读取token
	 * 
	 * @return
	 */
	public static String getAccessToken() {
		if (System.currentTimeMillis() - lastTime > 7100000) {
			try {
				ACCESSTOKEN = WechatUtil.readAccessToken(StringUtils.EMPTY);
				lastTime = System.currentTimeMillis();
			} catch (Exception e) {
				ACCESSTOKEN = StringUtils.EMPTY;
				e.printStackTrace();
			}
		}
		if (EAString.isNullStr(ACCESSTOKEN)) {
			try {
				ACCESSTOKEN = WechatUtil.readAccessToken(StringUtils.EMPTY);
				lastTime = System.currentTimeMillis();
			} catch (Exception e) {
				ACCESSTOKEN = StringUtils.EMPTY;
				e.printStackTrace();
			}
		}
		LOGGER.info("获取accessToken{}",ACCESSTOKEN);
		return ACCESSTOKEN;
	}

	/**
	 * 生产推广二维码
	 * 
	 * @param scene_id
	 * @return
	 */
	public static JSONObject getQRCodeTicket(int scene_id) {
		String data = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":" + scene_id	+ "}}}";
		String url = ConfigureManager.getWxConfig().getUrlConfigure().getTgqrcodeUrl(getAccessToken());
		LOGGER.info("生产推广二维码的url为：{}",url);
		JSONObject jsonObject = httpRequest(url, WechatConstant.REQUEST_METHOD_POST, data);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				return jsonObject;
			} catch (JSONException e) {
				e.printStackTrace();
				// 获取token失败
			}
		}
		return null;
	}

	/**
	 * 获取accessToken
	 * 获取accessToken 微信授权登陆 传code
	 */
	public static String readAccessToken(String code){
		String url = ConfigureManager.getWxConfig().getUrlConfigure().getAccessTokenUrl(ConfigureManager.getWxConfig().getAppId()
				,ConfigureManager.getWxConfig().getAppKey(),code);
		LOGGER.info("获取accessToken的url为：{}",url);
		String jsonStr = HttpRequest
				.sendPost(Configure.getAccessTokenUrl(Configure.getAppid(), Configure.getAppSecret()), StringUtils.EMPTY);
		JSONObject object = JSONObject.fromObject(jsonStr);
		String accessToken = object.getString(WechatConstant.ACCESS_TOKEN);
		LOGGER.info("获取的token为{}",accessToken);
		return accessToken;
	}

	/**
	 * 读取会员列表
	 * 
	 * @throws Exception
	 */
	public static JSONObject readUserList(String next_openid){
		try {
			String url = ConfigureManager.getWxConfig().getUrlConfigure().getUserListUrl(getAccessToken(),next_openid);
			LOGGER.info("读取会员列表的url为：{}",url);
			String jsonStr = new String(
					HttpRequest.sendPost(Configure.getUserListUrl(getAccessToken(), next_openid), StringUtils.EMPTY).getBytes(),
					WechatConstant.REQUEST_CHARSET);
			LOGGER.info("获取会员列表，获取到的会员信息:{}",jsonStr);
			JSONObject object = JSONObject.fromObject(jsonStr);
			return object;
		} catch (Exception e) {
			LOGGER.error("获取会员列表失败,失败信息{}",e);
		}
		return null;
	}
	
	
	

	/**
	 * 读取会员基础信息
	 * 
	 * @throws Exception
	 * 
	 */
	public static JSONObject readUserInfo(String openId) {
		try {
			String url = ConfigureManager.getWxConfig().getUrlConfigure().getUserInfoUrl(getAccessToken(),openId);
			LOGGER.info("网页授权获取用户OpenID的url为：{}",url);
			String jsonStr = new String(HttpRequest.sendGet(url, StringUtils.EMPTY).getBytes(),WechatConstant.REQUEST_CHARSET);
			LOGGER.info("openid{}，获取到的用户信息",jsonStr);
			JSONObject object = JSONObject.fromObject(jsonStr);
			return object;
		} catch (Exception e) {
			LOGGER.error("从微信获取用户基础信息错误,错误信息{}",e);
			return null;
		}
	}

	/**
	 * JSAPI凭证
	 * 
	 * @return
	 */
	public static String getJsapiTicket() {
		try {
			String url = ConfigureManager.getWxConfig().getUrlConfigure().getJsapiUrl(getAccessToken());
			LOGGER.info("网页授权获取用户OpenID的url为：{}",url);
			String jsonStr = HttpRequest.sendGet(url,StringUtils.EMPTY);
			JSONObject object = JSONObject.fromObject(jsonStr);
			LOGGER.info("获取到JSAPI凭证信息为{}",object.toString());
			return object.getString("ticket");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建一个用于微信浏览器的票据对象
	 * 
	 * @param url
	 * @return
	 */
	public static Map<String, String> createShareTicket(String url) {
		String access_token = getAccessToken();
		String jsapi_ticket = getJsapiTicket();
		Map<String, String> api = new HashMap<>();
		api.put("access_token", access_token);
		api.put("jsapi_ticket", jsapi_ticket);
		Map<String, String> ret = sign(jsapi_ticket, url);
		api.put("timestamp", ret.get("timestamp"));
		api.put("nonceStr", ret.get("nonceStr"));
		api.put("signature", ret.get("signature"));
		api.put("appId", Configure.getAppid());
		return api;
	}

	/**
	 * 网页授权获取用户OpenID
	 */
	public static String getOpenId(String code) {
		try {
			String url = ConfigureManager.getWxConfig().getUrlConfigure().getOpenIdUrl(ConfigureManager.getWxConfig().getAppId()
					,ConfigureManager.getWxConfig().getAppKey(),code);
			LOGGER.info("网页授权获取用户OpenID的url为：{}",url);
			String jsonStr = HttpRequest.sendPost(url, StringUtils.EMPTY);
			JSONObject object = JSONObject.fromObject(jsonStr);
			LOGGER.info("获取到的用户openid信息为：{}",object.toString());
			return object.getString("openid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}

	/**
	 * 创建菜单
	 */
	public static String createMenu(String params){
		String url = ConfigureManager.getWxConfig().getUrlConfigure().getCreateMenu(getAccessToken());
		LOGGER.info("请求 创建菜单url为：{}",url);
		String jsonStr = HttpRequest.sendPost(url, params);
		JSONObject object = JSONObject.fromObject(jsonStr);
		return object.getString("errmsg");
	}



	/**
	 * 查询所有模板列表
	 */
	public static String getPtemplateInfo(){
		String url = ConfigureManager.getWxConfig().getUrlConfigure().getTemplateListUrl(getAccessToken());
		LOGGER.info("请求所有模板的url为：{}",url);
		String jsonStr = HttpRequest.sendPost(url,StringUtils.EMPTY);
		return jsonStr;
	}

	/**
	 * 发送模板消息
	 * 
	 * @param template
	 * @return
	 */
	public static boolean sendTemplateMsg(Template template) {
		try {
			String url = ConfigureManager.getWxConfig().getUrlConfigure().getSendTemplateUrl(getAccessToken());
			LOGGER.info("发送模板消息url为：{}",url);
			JSONObject jsonResult = httpRequest(url, WechatConstant.REQUEST_METHOD_POST, template.toJSON());
			if (jsonResult != null) {
				int errorCode = jsonResult.getInt(WechatConstant.RESPONSE_CODE);
				String errorMessage = jsonResult.getString(WechatConstant.RESPONSE_MSG);
				LOGGER.info("发送模板消息结果，返回信息：{}，错误码为：{}",errorMessage,errorCode);
				return errorCode == 0;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			LOGGER.error("发送消息异常,异常信息{},",e);
			return Boolean.FALSE;
		}

	}

	

	/**
	 * 查询菜单
	 */
	public static String getMenuInfo() throws Exception {
		String jsonStr = HttpRequest
				.sendPost(ConfigureManager.getWxConfig().getUrlConfigure().getMenuInfoUrl(getAccessToken()), StringUtils.EMPTY);
		return jsonStr;
	}

	/**
	 * 签名
	 * 
	 * @param jsapi_ticket
	 * @param url
	 * @return
	 */
	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String signStr;
		String signature = StringUtils.EMPTY;
		// 注意这里参数名必须全部小写，且必须有序
		signStr = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		LOGGER.info("微信签名字符串为：{}",signStr);
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(signStr.getBytes(WechatConstant.REQUEST_CHARSET));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("没有提供算法异常，{}",e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("没有提供编码方式异常，{}",e);
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if (WechatConstant.REQUEST_METHOD_GET.equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes(WechatConstant.REQUEST_CHARSET));
				outputStream.close();
			}
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			LOGGER.error("Weixin server connection timed out.");
		} catch (Exception e) {
			LOGGER.error("https request error:{}", e);
		}finally {

		}
		return jsonObject;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return StringUtils.EMPTY;
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(WechatConstant.SpecialSymbol.AND);
			sb.append(String.join(WechatConstant.SpecialSymbol.EQUEL,entry.getKey(),entry.getValue().toString()));
		}
		String s = sb.toString();
		if (s.endsWith(WechatConstant.SpecialSymbol.AND)) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, WechatConstant.SpecialSymbol.AND);
		}
		return s;
	}

	/**
	 * 获取需要认证的URL,重定向URI会被执行Base64编码,
	 * 
	 * @param redirect_uri
	 * @return
	 */
	public static String getAuthorUrl(String redirect_uri) {
		redirect_uri = EAString.base64Encode(redirect_uri);
		int dc = EAString.countOccurrencesOf(redirect_uri, SpecificSymbolConstants.EQUEL);
		String url = EAString.delete(redirect_uri,  SpecificSymbolConstants.EQUEL);
		String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Configure.getAppid()
				+ "&redirect_uri=";
		if (EAString.isNullStr(redirect_uri)) {
			wxUrl = wxUrl + ConfigureManager.getSystemConfig().getDomain()  + "appwx/oauth2";
		} else {
			wxUrl = wxUrl + ConfigureManager.getSystemConfig().getDomain()  + "appwx/oauth2?zcurl=" + url + "%26dengHaoShu=" + dc;
		}
		wxUrl = wxUrl + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		return wxUrl;
	}

	/**
	 * 重写微信认证url
	 * 
	 */
	public static String getAuthorUrlUserInfo(String redirect_uri) {
		redirect_uri = EAString.base64Encode(redirect_uri);
		int dc = EAString.countOccurrencesOf(redirect_uri, SpecificSymbolConstants.EQUEL);
		String url = EAString.delete(redirect_uri, SpecificSymbolConstants.EQUEL);
		String wxUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Configure.getAppid()
				+ "&redirect_uri=";
		if (EAString.isNullStr(redirect_uri)) {
			wxUrl = wxUrl +  ConfigureManager.getSystemConfig().getDomain() + "appwx/oauth2";
		} else {
			wxUrl = wxUrl +  ConfigureManager.getSystemConfig().getDomain() +"appwx/oauth2?zcurl=" + url + "%26dengHaoShu=" + dc;
		}
		wxUrl = wxUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		return wxUrl;
	}

	/**
	 * 获取需要认证的URL,重定向URI会被执行Base64编码,
	 * 
	 * @param redirect_uri
	 * @return
	 */
	public static String getRedirectUrl(String redirect_uri, int dengHaoShu) {
		for (int i = 0; i < dengHaoShu; i++) {
			redirect_uri = redirect_uri +  SpecificSymbolConstants.EQUEL;
		}
		redirect_uri = EAString.base64Decode(redirect_uri);
		return redirect_uri;
	}

	/**
	 * 根据url获取指定的参数
	 * 
	 */
	public static PageData getParamValueByUrl(String redirectUrl) {
		String paramUrl = redirectUrl.substring(redirectUrl.indexOf("?") + 1, redirectUrl.length());
		String[] paramAry = paramUrl.split(SpecificSymbolConstants.AMPERSAND);
		PageData paramMap = new PageData();
		for (String s : paramAry) {
			String[] singleParam = s.split( SpecificSymbolConstants.EQUEL);
			paramMap.put(singleParam[0], singleParam[1]);
		}
		return paramMap;
	}

	public static String getMediaInfo(String s) {
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + getAccessToken() + "&media_id=" + s;
		return HttpRequest.sendGetForMedia(url, StringUtils.EMPTY);
	}
}
