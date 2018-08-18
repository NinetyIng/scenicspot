package com.ythd.ower.wx.model;

import com.ythd.ower.wx.common.WechatUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
public class AppModel {
	private static AppModel __model=null;  
	//private WXAPIModel apiModel=new WXAPIModel();
	
	public void apiTimer() {  
        Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
            	//createApi();
            }  
        }, 110*60*60*1000);// 设定指定的时间time,此处为2000毫秒  
    }	
	
	public WXAPIModel getApiModel(String url){
		String access_token= WechatUtil.getAccessToken();
		String jsapi_ticket=WechatUtil.getJsapiTicket();
		WXAPIModel api=new WXAPIModel();
		api.setAccess_token(access_token);
		api.setJsapi_ticket(jsapi_ticket);
		Map<String, String> ret = sign(api.getJsapi_ticket(),url);//);
		api.setTimestamp(ret.get("timestamp"));
		api.setNonceStr(ret.get("nonceStr"));
		api.setSignature(ret.get("signature"));
		return api;
	}
	
	
	public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = WechatUtil.byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
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
	public AppModel(){
		//createApi();
	}
    /**
     * 得到单例示例
     * @return
     */
    public static AppModel getInstance() {  
         if (__model == null) {    
        	 __model = new AppModel();
         }    
        return __model;  
    }
}
