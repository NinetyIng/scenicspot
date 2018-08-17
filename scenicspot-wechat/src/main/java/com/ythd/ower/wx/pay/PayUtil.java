package com.ythd.ower.wx.pay;

import com.tencent.WXPay;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.XMLParser;
import com.ythd.ower.common.constants.SpecificSymbolConstants;
import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.common.tools.HttpClientSSLUtils;
import com.ythd.ower.common.tools.MD5;
import com.ythd.ower.data.config.ConfigureManager;
import com.ythd.ower.wx.common.WechatConstant;
import com.ythd.ower.wx.common.WechatUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;


public class PayUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PayUtil.class);

	private static final String APPID = "appid";

	private static final String BODY = "body";

	private static final String  MCH_ID = "mch_id";

	private static final String NONCE_STR = "nonce_str";

	private static final String NOTIFY_URL = "notify_url";

	private static final String OPENID = "openid";

	private static final String OUT_TRADE_NO = "out_trade_no";

	private static final String SPBILL_CREATE_IP = "spbill_create_ip";

	private static final String TOTAL_FEE = "total_fee";

	private static final String TRADE_TYPE = "trade_type";

	private static final String SIGN = "sign";

	private static final String RETURN_CODE = "return_code";
	private static final String RESULT_CODE = "result_code";

	private static final String RETURN_SUCCESS = "SUCCESS";

	private static final String NONCESTR = "noncestr";

	private static final String PACKAGE = "package";

	private static final String PREPAY_ID = "prepay_id";

	private static final String SIGNTYPE = "signType";
	private static final String TIMESTAMP = "timeStamp";



	
	public static String toXml(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");
			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");
		LOGGER.info("拼接的xml参数信息为：{}",sb);
		return sb.toString();
	}
	/**
	 * 微信签名
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String genPackageSign(List<NameValuePair> params){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append(SpecificSymbolConstants.EQUEL);
			sb.append(params.get(i).getValue());
			sb.append(SpecificSymbolConstants.AMPERSAND);
		}
		sb.append("key=");
		sb.append(ConfigureManager.getWxConfig().getPayConfigure().getPayAppKey());
		String sign =  MD5.MD5Encode(sb.toString(), WechatConstant.REQUEST_CHARSET).toUpperCase();
		return sign;
	}
	
	/**
	 * 场馆支付签名
	 * 
	 */
	public static PageData wxPaySign(String pay_money, String billNo, String body, String open_id) throws ParserConfigurationException,IOException ,SAXException{
		PageData resultParam = new PageData();
		List<NameValuePair> signParams = new LinkedList<>();
		WXPay.initSDKConfiguration(null,ConfigureManager.getWxConfig().getPayConfigure().getPayAppId(),ConfigureManager.getWxConfig().getPayConfigure().getPayMchtId(), null, null, null);
		String totalmoney = String.format("%.0f",
				BigDecimal.valueOf(Double.parseDouble(pay_money)).doubleValue() * 100);
		signParams.add(new BasicNameValuePair(APPID, ConfigureManager.getWxConfig().getPayConfigure().getPayAppId()));
		signParams.add(new BasicNameValuePair(BODY, String.valueOf(body)));
		signParams.add(new BasicNameValuePair(MCH_ID, ConfigureManager.getWxConfig().getPayConfigure().getPayMchtId()));
		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		signParams.add(new BasicNameValuePair(NONCE_STR, nonce_str));
		signParams.add(new BasicNameValuePair(NOTIFY_URL, ConfigureManager.getWxConfig().getPayConfigure().getNotifyUrl()));
		/*resultParam.put("nonceStr", nonce_str);*/
		signParams.add(new BasicNameValuePair(OPENID, open_id));
		signParams.add(new BasicNameValuePair(OUT_TRADE_NO, billNo));
		resultParam.put(OUT_TRADE_NO, billNo);
		signParams.add(new BasicNameValuePair(SPBILL_CREATE_IP, "58.250.174.48"));
		signParams.add(new BasicNameValuePair(TOTAL_FEE, totalmoney));
		signParams.add(new BasicNameValuePair(TRADE_TYPE, "JSAPI"));
		String sign = PayUtil.genPackageSign(signParams);
		signParams.add(new BasicNameValuePair(SIGN, sign));

		byte[] data = HttpClientSSLUtils.httpPost(ConfigureManager.getWxConfig().getUrlConfigure().getUnifiedOrderUrl(), PayUtil.toXml(signParams));
		String result = new String(data);
		Map<String, Object> mapdata = XMLParser.getMapFromXML(result);
		if (mapdata != null && RETURN_SUCCESS.equals(mapdata.get(RETURN_CODE)) && RETURN_SUCCESS.equals(mapdata.get(RESULT_CODE))) {
			String timestamp = (System.currentTimeMillis() / 1000) + "";
			List<NameValuePair> sencondSign = new LinkedList<>();
			sencondSign.add(new BasicNameValuePair(APPID, ConfigureManager.getWxConfig().getPayConfigure().getPayAppId()));
			String noncestrS = RandomStringGenerator.getRandomStringByLength(32);
			sencondSign.add(new BasicNameValuePair(NONCESTR, noncestrS));
			sencondSign.add(new BasicNameValuePair(PACKAGE, String.join(SpecificSymbolConstants.EQUEL,PREPAY_ID,(String)mapdata.get(PREPAY_ID))));
			sencondSign.add(new BasicNameValuePair(SIGNTYPE, "MD5"));
			sencondSign.add(new BasicNameValuePair(TIMESTAMP, timestamp));
			String signS = PayUtil.genPackageSign(sencondSign);
			resultParam.put("paySign", signS);
			resultParam.put(TIMESTAMP, timestamp);
			resultParam.put("pack_age", String.join(SpecificSymbolConstants.EQUEL,PREPAY_ID,(String)mapdata.get(PREPAY_ID)));
			resultParam.put(NONCESTR, noncestrS);
			return resultParam;
		}
		return null;
	}

}
