package com.ythd.ower.wx.common;

import com.ythd.ower.common.tools.FtpUtilsAbove;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;

public class HttpRequest {
	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestProperty("Charsert", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }





    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGetForMedia(String url, String param) {
        String result = "",urlNameString;
        BufferedInputStream bin = null;
        try {
            if(StringUtils.isNotEmpty(param)){
               urlNameString = url + "?" + param;
            }else{
               urlNameString = url;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setRequestProperty("Charsert", "UTF-8");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            bin = new BufferedInputStream(connection.getInputStream());
            FtpUtilsAbove ftpUtilsAbove = new FtpUtilsAbove();
            result = ftpUtilsAbove.upload(bin, "wzd", "imgs");
            return result;
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (bin != null) {
                	bin.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = org.apache.commons.lang3.StringUtils.EMPTY;
        try {
            URL realUrl = new URL(url);
           /* param=new String(param.getBytes(), "utf-8");*/
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),WechatConstant.REQUEST_CHARSET));
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String JSON="json";
    public static String XML="xml";
    public static String TEXT="text";
    /**
   	 * 发起https请求并获取结果
   	 *
   	 * @param requestUrl 请求地址
   	 * @param requestMethod 请求方式（GET、POST）
   	 * @param outputStr 提交的数据
   	 * @param resultType 返回类型(json,xml,text) 请使用静态变量
   	 * @return Object
   	 */
   	public static Object sendHttpsRequest(String requestUrl, String requestMethod, String outputStr,String resultType) {
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
   				// 注意编码格式，防止中文乱码
   				outputStream.write(outputStr.getBytes(WechatConstant.REQUEST_CHARSET));
   				outputStream.close();
   			}
   			// 将返回的输入流转换成字符串
   			InputStream inputStream = httpUrlConn.getInputStream();
   			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, WechatConstant.REQUEST_CHARSET);
   			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
   			String str = null;
   			while ((str = bufferedReader.readLine()) != null) {
   				buffer.append(str);
   			}
   			bufferedReader.close();
   			inputStreamReader.close();
   			// 释放资源
   			inputStream.close();
   			inputStream = null;
   			httpUrlConn.disconnect();
   			System.out.println("JSON Source String="+buffer);
   			if(JSON.equalsIgnoreCase(resultType)){
   				JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
   	   			return jsonObject;
   			}else if(XML.equals(resultType)){

   			}else{
   				return buffer.toString();
   			}
   		} catch (ConnectException ce) {
   			log.error("Weixin server connection timed out.");
   		} catch (Exception e) {
   			log.error("https request error:{}", e);
   		}
   		return null;
   	}
}