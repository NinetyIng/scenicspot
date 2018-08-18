package com.ythd.ower.wx.pay;

import com.ythd.ower.wx.common.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 获取微信预下单编号
 * 
 * @author Leno
 *
 */
public class GetWxOrderNo {
	public static Map doXML(String url, String xmlParam) {
		Map map = new HashMap();
		try {
			String jsonStr = HttpRequest.sendPost(url, xmlParam);
			System.out.println(jsonStr);
			map = doXMLParse(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public static String getPayNo(String url, String xmlParam) {
		System.out.println("xml是:" + xmlParam);
		String prepay_id = "";
		try {
			String jsonStr = HttpRequest.sendPost(url, xmlParam);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			System.out.println("json是:" + jsonStr);
			if (jsonStr.indexOf("FAIL") != -1) {
				return prepay_id;
			}
			Map map = doXMLParse(jsonStr);
			String return_code = (String) map.get("return_code");
			prepay_id = (String) map.get("prepay_id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prepay_id;
	}

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * 
	 * @param strxml
	 * @return
	 * @throws Exception
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = string2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Reader xmlStreamReader = new InputStreamReader(in, "gbk");
		Document doc = builder.build(xmlStreamReader);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			m.put(k, v);
		}
		// 关闭流
		in.close();
		return m;
	}

	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}

	public static InputStream string2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

}