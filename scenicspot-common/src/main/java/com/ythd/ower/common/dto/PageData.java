package com.ythd.ower.common.dto;

import com.ythd.ower.common.tools.TypeCaseHelper;
import com.ythd.ower.common.tools.WebUtil;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * 说明：参数封装Map
 * 创建人：FH Q371855779
 * 修改时间：2014年9月20日
 * @version
 */
@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey();
			value="";
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					 value = value+values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	@SuppressWarnings("unchecked")
	public PageData(MultipartHttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Entry) entries.next();
			name = (String) entry.getKey(); 
			value="";
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = value+values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}

	public PageData getAsPageData(Object key) {
		try{
			PageData obj = (PageData) map.get(key);
			return obj;
		}catch(Exception e){
			return null;
		}
	}
	
	public List getAsList(Object key) {
		try{
			List obj = (List) map.get(key);
			return obj;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		return (String)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
	
	/**
	 * 以BigDecimal类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return BigDecimal 键�?
	 */
	public BigDecimal getAsBigDecimal(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (BigDecimal) obj;
		else
			return BigDecimal.ZERO;
	}
	/**
	 * 以Date类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Date 键�?
	 */
	public Date getAsDate(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}
	
	public Date getAsDateTime(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	/**
	 * 以Integer类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Integer 键�?
	 */
	public Integer getAsInteger(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Integer", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}
	/**
	 * 得到一个Int,如果为空的话会返回-1
	 */
	public Integer getAsInt(String pStr){
		Integer value=getAsInteger(pStr);
		if(value!=null){
			return value.intValue();
		}
		return -1;
	}
	/**
	 * 以Long类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Long 键�?
	 */
	public Long getAsLong(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Long", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}
	/**
	 * 以float类型返回键值; 未找到时返回-1;
	 * 
	 * @param key 键名
	 * @return fong 键值
	 */
	public float getAsFloat(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Float", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Float) obj;
		else
			return -1;
	}
	/**
	 * 以Long类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Long 键�?
	 */
	public Double getAsDouble(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Double", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Double) obj;
		else
			return null;
	}
	/**
	 * 以String类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return String 键�?
	 */
	public String getAsString(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "String", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (String) obj;
		else
			return "";
	}
	
	/**
	 * 以List类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return List 键�?
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> getAsList(String key){
		return (List<PageData>)get(key);
	}

	/**
	 * 以Timestamp类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Timestamp 键�?
	 */
	public Timestamp getAsTimestamp(String key) {
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}
	
	/**
	 * 以Boolean类型返回键�?
	 * 
	 * @param key
	 *            键名
	 * @return Timestamp 键�?
	 */
	public Boolean getAsBoolean(String key){
		Object obj = null;
		try {
			obj = TypeCaseHelper.convert(get(key), "Boolean", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj != null)
			return (Boolean) obj;
		else
			return null;
	}

	/**
	 * 给Dto压入第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过�?
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public void setDefaultAList(List pList) {
		put("defaultAList", pList);
	}

	/**
	 * 给Dto压入第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过�?
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public void setDefaultBList(List pList) {
		put("defaultBList", pList);
	}

	/**
	 * 获取第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过�?
	 * 
	 *            压入Dto的List对象
	 */
	public List getDefaultAList() {
		return (List) get("defaultAList");
	}

	/**
	 * 获取第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过�?
	 * 
	 *            压入Dto的List对象
	 */
	public List getDefaultBList() {
		return (List) get("defaultBList");
	}
	
    /**
     * 给Dto压入�?��默认的Json格式字符�?
     * @param jsonString
     */
	public void setDefaultJson(String jsonString){
    	put("defaultJsonString", jsonString);
    }
    
    /**
     * 获取默认的Json格式字符�?
     * @return
     */
    public String getDefaultJson(){
    	return getAsString("defaultJsonString");
    }

	
	
	/**
	 * 设置交易状�?
	 * 
	 * @param pSuccess
	 */
	public void setSuccess(Boolean pSuccess){
		put("success", pSuccess);
		if (pSuccess) {
			//put("bflag", "1");
		}else {
			//put("bflag", "0");
		}
		
	}
	
	/**
	 * 获取交易状�?
	 * 
	 */
	public Boolean getSuccess(){
		return getAsBoolean("success");
	}
	
	/**
	 * 设置交易提示信息
	 * 
	 */
	public void setMsg(String pMsg){
		put("msg", pMsg);
	}
	
	/**
	 * 获取交易提示信息
	 * 
	 */
	public String getMsg(){
		return getAsString("msg");
	}
	
	/**
	 * 打印DTO对象
	 * 
	 */
	public void println(){
		System.out.println(this);
	}


	public void setDefaultCList(List pList) {
		put("defaultCList", pList);
		
	}
	public List getDefaultCList() {
		return (List) get("defaultCList");
	}
	

	/**
	 * 将此Dto对象转换为Json格式字符�?br>
	 *
	 * @return string 返回Json格式字符�?
	 */
	public String toJson() {
		String strJson = null;
		strJson = WebUtil.encodeObject2Json(this);
		return strJson;
	}

}
