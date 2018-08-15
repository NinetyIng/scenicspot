package com.ythd.ower.common.properties;

import java.io.InputStream;

import com.ythd.ower.common.dto.PageData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Properties文件静�?工厂
 * 
 * @author BLRISE
 * @since 2013-08-2
 */
@SuppressWarnings("unchecked")
public class PropertiesFactory {

	private static Log log = LogFactory.getLog(PropertiesFactory.class);
	/**
	 * 属�?文件实例容器
	 */
	private static PageData container = new PageData();

	static {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		// 加载属�?文件global.sys.properties
		try {
			InputStream is = classLoader.getResourceAsStream("config.system.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesFile.SYS, ph);
		} catch (Exception e1) {
			log.error("加载系统配置文件出错，错误{}",e1);
		}
		try{
			InputStream payIo = classLoader.getResourceAsStream("config.pay.properties");
			container.put(PropertiesFile.PAY, new PropertiesHelper(payIo));
		}catch(Exception e){
			log.error("加载支付配置文件出错，错误{}",e);
		}

		try{
			InputStream ticketIo = classLoader.getResourceAsStream("config.ticket.properties");
			container.put(PropertiesFile.TICKET, new PropertiesHelper(ticketIo));
		}catch(Exception e){
			log.error("加载支付配置文件出错，错误{}",e);
		}
	}
	/**
	 * 获取属�?文件实例
	 * 
	 * @param pFile
	 *            文件类型
	 * @return 返回属�?文件实例
	 */
	public static PropertiesHelper getPropertiesHelper(String pFile) {
		PropertiesHelper ph = (PropertiesHelper) container.get(pFile);
		return ph;
	}
}
