package com.ythd.ower.wx.common;


import com.ythd.ower.common.tools.EAString;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 14:40
 * 这里放置各种配置数据
 */
public class Configure {
	//黄道
	private static String appID = "wxd1698399bec842cf";//黄道微公众号 wxd1698399bec842cf
	private static String appSecret="09c204558c6bf251aaec34915d161a56";//黄道微appsecret09c204558c6bf251aaec34915d161a56
	private static String key = "00b38b1993b74701a8eb53c36ea98259";//移商公众号
	private static String mchID = "1457494302";//Berlins

	//是否使用异步线程的方式来上报API测速，默认为异步模式
	private static boolean useThreadToDoReport = true;

	//机器IP
	private static String ip = "";

	//以下是几个API的路径：
	//1）被扫支付API
	public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

	//2）被扫支付查询API
	public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

	//3）退款API
	public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	//4）退款查询API
	public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

	//5）撤销API
	public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

	//6）下载对账单API
	public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

	//7) 统计上报API
	public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

	//8)预下订单
	public static String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	public static boolean isUseThreadToDoReport() {
		return useThreadToDoReport;
	}

	public static void setUseThreadToDoReport(boolean useThreadToDoReport) {
		Configure.useThreadToDoReport = useThreadToDoReport;
	}

	public static String HttpsRequestClassName = "com.tencent.common.HttpsRequest";


	public static void setKey(String key) {
		Configure.key = key;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}


	public static void setIp(String ip) {
		Configure.ip = ip;
	}

	public static String getKey(){
		return key;
	}
	
	public static String getAppid(){
		return appID;
	}
	
	public static String getMchid(){
		return mchID;
	}

	

	public static String getIP(){
		return ip;
	}

	public static void setHttpsRequestClassName(String name){
		HttpsRequestClassName = name;
	}


	public static String getAppSecret() {
		return appSecret;
	}
	/**
	 * 读取ACCESS_TOKEN地址
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessTokenUrl(String appid, String secret){
		return "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret=" + secret;
	}
	/**
	 * 读取会员列表URL
	 * @param accessToken
	 * @return
	 */
	public static String getUserListUrl(String accessToken,String next_openid){
		if(EAString.isNullStr(next_openid)){
			return "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken;
		}
		return "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid="+next_openid;
	}
	/**
	 * 读取指定用户的基本信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static String getUserInfoUrl(String accessToken,String openId){
		String userInfoUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
		userInfoUrl=userInfoUrl+accessToken+"&openid="+openId+"&lang=zh_CN";
		return userInfoUrl;
	}
	/**
	 * 微信授权登陆获取token
	 * 
	 */
	public static String getAccessTokenUrl(String appid,String secret,String code){
		String  accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";
		accessTokenUrl = accessTokenUrl+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		return accessTokenUrl;
	}
}
