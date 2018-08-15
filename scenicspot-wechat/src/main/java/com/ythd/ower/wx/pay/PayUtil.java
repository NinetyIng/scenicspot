package com.ythd.ower.wx.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.SAXException;

import com.easaa.core.util.MD5;
import com.easaa.core.util.Util;
import com.easaa.entity.PageData;
import com.easaa.wechat.common.Configure;
import com.tencent.WXPay;
import com.tencent.common.RandomStringGenerator;
import com.tencent.common.XMLParser;

public class PayUtil {
	
	
	public static String toXml(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");
			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");
		System.out.println("xml<><><><><><><>><><><>" + sb.toString());
		return sb.toString();
	}
	/**
	 * 微信签名
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String genPackageSign(List<NameValuePair> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
			System.out.println("key:" + params.get(i).getName() + "    value:" + params.get(i).getValue());
		}
		sb.append("key=");
		sb.append(Configure.getKey());
		System.out.println(Configure.getKey());
		String sign =  MD5.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		System.out.println(sign);
		return sign;
	}
	
	/**
	 * 场馆支付签名
	 * 
	 */
	public static  PageData wxSignPack(String pay_money,String billNo,String body,String open_id) throws ParserConfigurationException, IOException, SAXException {
		PageData resultParam = new PageData();
		List<NameValuePair> signParams = new LinkedList<NameValuePair>();
		WXPay.initSDKConfiguration(null, Configure.getAppid(), Configure.getMchid(), null, null, null);
		String totalmoney = String.format("%.0f",
				BigDecimal.valueOf(Double.parseDouble(pay_money)).doubleValue() * 100); // 支付金额（元）
		signParams.add(new BasicNameValuePair("appid", Configure.getAppid())); // 应用ID
		signParams.add(new BasicNameValuePair("body", String.valueOf(body))); // 商品描述
		signParams.add(new BasicNameValuePair("mch_id", Configure.getMchid())); // 商户号
		String nonce_str = RandomStringGenerator.getRandomStringByLength(32);
		signParams.add(new BasicNameValuePair("nonce_str", nonce_str)); // 随机字符串
		signParams.add(new BasicNameValuePair("notify_url", Configure.notify_url)); // 回调地址
		/*resultParam.put("nonceStr", nonce_str);*/
		signParams.add(new BasicNameValuePair("openid", open_id)); // openid
		signParams.add(new BasicNameValuePair("out_trade_no", billNo)); // 商户订单号
		resultParam.put("out_trade_no", billNo);
		signParams.add(new BasicNameValuePair("spbill_create_ip", "58.250.174.48")); // 终端IP
		signParams.add(new BasicNameValuePair("total_fee", totalmoney)); // 总金额
		signParams.add(new BasicNameValuePair("trade_type", "JSAPI")); // 交易类型
		String sign = PayUtil.genPackageSign(signParams);
		signParams.add(new BasicNameValuePair("sign", sign)); // 签名
		byte[] data = Util.httpPost(Configure.UNIFIEDORDER, PayUtil.toXml(signParams)); //第一次签名认证，wx_url接口链接
		String result = new String(data);
		Map<String, Object> mapdata = XMLParser.getMapFromXML(result);
		if (mapdata != null && "SUCCESS".equals(mapdata.get("return_code")) && "SUCCESS".equals(mapdata.get("result_code"))) {
			String timestamp = (System.currentTimeMillis() / 1000) + "";
			List<NameValuePair> sencondSign = new LinkedList<NameValuePair>();
			sencondSign.add(new BasicNameValuePair("appId", Configure.getAppid()));
			String noncestrS = RandomStringGenerator.getRandomStringByLength(32);
			sencondSign.add(new BasicNameValuePair("nonceStr", noncestrS));
			sencondSign.add(new BasicNameValuePair("package", "prepay_id="+(String)mapdata.get("prepay_id")));
			/*sencondSign.add(new BasicNameValuePair("partnerid", Configure.getMchid()));*/
			sencondSign.add(new BasicNameValuePair("signType", "MD5"));
			sencondSign.add(new BasicNameValuePair("timeStamp", timestamp));
			String signS = PayUtil.genPackageSign(sencondSign);
			resultParam.put("paySign", signS);
			resultParam.put("timestamp", timestamp);
			resultParam.put("pack_age", "prepay_id="+(String)mapdata.get("prepay_id"));
			resultParam.put("nonceStr", noncestrS);
			return resultParam;
		}
		return null;
	}
	
	
	
}
