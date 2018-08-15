package com.ythd.ower.common.reflex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.ibox.JavaBeanDescriptor;
import com.ythd.ower.common.tools.JavaBeanSerializeUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/** 
 * 说明：反射工具
 * 创建人：FH Q371855779
 * 修改时间：2014年9月20日
 * @version
 */
public class ReflectHelper {

	private static final List<String> reqExcludes = new CopyOnWriteArrayList();
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}

	public static GenericResponseDto getSerializeObject(Object obj) {
		if (obj instanceof JavaBeanDescriptor) {
			return (GenericResponseDto) JavaBeanSerializeUtil.deserialize((JavaBeanDescriptor)obj);
		} else if (obj instanceof GenericResponseDto) {
			return (GenericResponseDto)obj;
		} else {
			GenericResponseDto resp = new GenericResponseDto();
			Map<String, Object> map = jsonToMap(obj);
			if (map != null && !map.isEmpty()) {
				resp.putAll(map);
				return resp;
			} else {
				return resp;
			}
		}
	}

	private static Map<String, Object> jsonToMap(Object obj) {
		if (obj == null) {
			return null;
		} else {
			String jsonString = JSON.toJSONString(obj, getReqExcludeFilters(), new SerializerFeature[]{SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField});
			return (Map)JSON.parseObject(jsonString, Map.class);
		}
	}
	public static SerializeFilter[] getReqExcludeFilters() {
		List<SerializeFilter> filters = new ArrayList();
		Iterator var1 = reqExcludes.iterator();

		while(var1.hasNext()) {
			String item = (String)var1.next();
			SimplePropertyPreFilter preFilter = new SimplePropertyPreFilter(new String[0]);
			preFilter.getExcludes().add(item);
			filters.add(preFilter);
		}

		SerializeFilter[] filterArray = new SerializeFilter[filters.size()];
		return (SerializeFilter[])filters.toArray(filterArray);
	}
}
