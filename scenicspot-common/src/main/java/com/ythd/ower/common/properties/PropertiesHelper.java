package com.ythd.ower.common.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Properties处理�?
 * @author BLRISE
 * @since 2013-07-10
 */
public class PropertiesHelper {
	
	private static PropertiesHelper _SYS_HELPER;
	private static PropertiesHelper _APP_HELPER;
	public static final PropertiesHelper getSysInstance(){
		if(_SYS_HELPER==null){
			_SYS_HELPER = PropertiesFactory.getPropertiesHelper(PropertiesFile.SYS);
		}
		return _SYS_HELPER;
	}
	
	public static PropertiesHelper getAppInstance(){
		if(_APP_HELPER==null){
			_APP_HELPER = PropertiesFactory.getPropertiesHelper(PropertiesFile.APP);
		}
		return _APP_HELPER;
	}
	
	/**
	 * 获取一个系统配置值
	 * @param key
	 * @return
	 */
	public static String getSysValue(String key){
		return getSysInstance().getValue(key);
	}
	
	public static String getAppRootUrl(){
		return getSysInstance().getValue("web_root_url");
		
	}
	public static String getWechatUrl(){
		return getSysInstance().getValue("wechat_url");
	}
	
	public static String getAccessTokenUrl(){
		return getSysInstance().getValue("access_token_url");
	}
	
	
	public static String getAppId(){
		return getSysInstance().getValue("APPID");
	}
	/**
	 * 获取一个APP配置
	 * @param key
	 * @return
	 */
	public static String getAppValue(String key){
		return getAppInstance().getValue(key);
	}
	
	private static Log log = LogFactory.getLog(PropertiesHelper.class);
	private Properties objProperties;
	
	/**
	 * 构�?函数
	 * @param is 属�?文件输入�?
	 * @throws Exception
	 */
	public PropertiesHelper(InputStream is) throws Exception {
		try{
			objProperties = new Properties();
			objProperties.load(new InputStreamReader(is,"utf-8"));
		}
		catch(FileNotFoundException e){
			throw e;
		}
		catch(Exception e){
			throw e;
		}finally{
			is.close();
		}
	}

    /**
     * 持久化属性文�?br>
     * 使用setProperty()设置属�?�?必须调用此方法才能将属�?持久化到属�?文件�?
     * @param pFileName 属�?文件�?
     * @throws IOException 
     */
	public void storefile(String pFileName){
		FileOutputStream outStream = null;
		try{
			File file = new File(pFileName + ".properties");
			outStream = new FileOutputStream(file);
			objProperties.store(outStream, "#BL3EE");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

    /**
     * 获取属�?�?
     * @param key 指定Key值，获取value
     * @return String 返回属�?�?
     */
	public String getValue(String key){
		return objProperties.getProperty(key);
	}

    /**
     * 获取属�?�?支持缺省设置
     * @param key
     * @param defaultValue 缺省�?
     * @return String 返回属�?�?
     */
	public String getValue(String key, String defaultValue){
		return objProperties.getProperty(key, defaultValue);
	}

    /**
     * 删除属�?
     * @param key 属�?Key
     */
	public void removeProperty(String key){
		objProperties.remove(key);
	}
	
    /**
     * 设置属�?
     * @param key 属�?Key
     * @param value 属�?�?
     */
	public void setProperty(String key, String value){
		objProperties.setProperty(key, value);
	}
	
    /**
     * 打印�?��属�?�?
     */
	public void printAllVlue(){
		 objProperties.list(System.out);
	}
}
