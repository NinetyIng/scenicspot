package com.ythd.ower.common.tools;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

public abstract class EABeans extends BeanUtils {
	    /**
	     * 将JavaBean转换成Map
	     * 
	     * @return
	     */
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static Map beanToMap(Object bean) {

	        Map map = new HashMap();
	        Field[] fields = bean.getClass().getDeclaredFields();
	        BeanMap beanMap = new BeanMap(bean);
	        Set set = beanMap.keySet();
	        Iterator iter = set.iterator();
	        while (iter.hasNext()) {
	            Object key = iter.next();
	            for (Field field : fields) {
	                if (field.getName().equals(key)) {
	                    Object value = beanMap.get(key);
	                    if (value == null || value instanceof Integer || value instanceof String
	                            || value instanceof Date || value instanceof Double
	                            || value instanceof BigDecimal || value instanceof Character
	                            || value instanceof Boolean || value instanceof Float
	                            || value instanceof Short || value instanceof Long
	                            || value instanceof Byte) {
	                        map.put(key, value != null ? value : "");
	                    }
	                    break;
	                }
	            }
	        }
	        return map;
	    }
	    
	    /**
	     * 将bean的fieldName转化成in
	     * 
	     * @param list
	     * @param fieldName
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		public static String convertListToIn(List list, String fieldName) {

	        StringBuffer sb = new StringBuffer("(");
	        String methodName = "getId";
	        if (fieldName != null) {
	            methodName = "get" + (fieldName.charAt(0) + "").toUpperCase() + fieldName.substring(1);
	        }
	        for (int i = 0; i < list.size() - 1; i++) {
	            sb.append("'").append(getFieldValue(list.get(i), methodName)).append("',");
	        }
	        sb.append("'").append(
	                list.size() > 0 ? getFieldValue(list.get(list.size() - 1), methodName) : "")
	                .append("'");
	        sb.append(")");
	        return sb.toString();
	    }
	    
	    public static String getFieldValue(Object obj, String methodName) {

	        String value = "";
	        try {
	            value = (String) obj.getClass().getMethod(methodName).invoke(obj);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return value;
	    }
	    
	    /**
	     * 
	     * 取值属性值，如果对象没有相应的属性，则制造对应的方法，不一定需要get,或is开头
	     * 
	     * @param obj
	     * @param propertyExpression beanutils的表达式
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		public static Object getAnyProperty(final Object obj, String propertyExpression) {

	        if (obj == null || propertyExpression == null || propertyExpression.trim().length() < 1) {
	            
	            return null;
	        }
	        
	        String[] names = propertyExpression.split("\\.");
	        
	        Object result = obj;
	        Object temp = null;
	        
	        for (int i = 0; i < names.length; i++) {
	            
	            Matcher m = itratorPtn.matcher(names[i]);
	            String propertyName = null;
	            
	            if (m.find()) {
	                propertyName = m.group(1);
	                names[i] = names[i].substring(0, names[i].indexOf("["));
	            }
	            
	            if (temp != null) {
	                result = temp;
	                temp = null;
	            } else {
	                result = EABeans.getSimpleValue(result, names[i]);
	            }
	            
	            if (result == null) {
	                // 如果是空值则退出
	                break;
	            }
	            
	            if ((result instanceof Collection)) {
	                // 如果是集合
	                // log.debug(" result is a Collection");
	                Collection c = (Collection) result;
	                c.size();// 初始化
	                
	                if (propertyName != null) {
	                    for (Iterator iterator = c.iterator(); iterator.hasNext();) {
	                        EABeans.getSimpleValue(iterator.next(), propertyName);
	                    }
	                }
	            } else if (result instanceof Map) {
	                
	                // 如果是Map
	                // log.debug(" result is a Map");
	                Map map = (Map) result;
	                map.size();
	                
	                if (propertyName != null) {
	                    for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
	                        EABeans.getSimpleValue(iterator.next(), propertyName);
	                    }
	                }
	                
	                temp = map.get(names[i]);
	            } else if (result.getClass().isArray()) {
	                
	                // 如果数组
	                try {
	                    temp = Array.get(result, Integer.parseInt(names[i]));
	                } catch (Exception e) {
	                    wrapThrow(e, "get array property from [%s]", obj.getClass()
	                            .getSimpleName());
	                    // log.debug("get arrary[" + names[i] + "] error,", e);
	                }
	            }
	        }
	        
	        return result;
	    }
	    
	    /**
	     * 获取某个对象的getter属性值
	     * @param obj 对象 
	     * @param property 属性
	     * @return
	     */
	    @SuppressWarnings("rawtypes")
		public static Object getSimpleValue(Object obj, String property) {

	        Object result = null;
	        try {
	            
	            result = PropertyUtils.getSimpleProperty(obj, property);
	        } catch (Throwable e) {
	            try {
	                result = MethodUtils.invokeExactMethod(obj, property, new Object[] {});
	            } catch (Throwable ex) {
	                try {
	                    Field field = obj.getClass().getField(property);
	                    boolean p = field.isAccessible();
	                    field.setAccessible(true);
	                    result = field.get(obj);
	                    field.setAccessible(p);
	                } catch (Exception exx) {
	                    wrapThrow(exx, "getField %s.%s error!", obj.getClass().getSimpleName(),
	                            property);
	                }
	            }
	        }
	        
	        // 最后如果是集，则初始化
	        if ((result instanceof Collection)) {
	            // 如果是集合}
	            Collection c = (Collection) result;
	            c.size();// 初始化
	        } else if (result instanceof Map) {
	            // 如果是Map
	            Map map = (Map) result;
	            map.size();
	        } else if (result != null) {
	            result.toString();//
	        }
	        return result;
	    }
	    
	    // 集合
	    public static Pattern itratorPtn = Pattern.compile("\\[(\\w+)\\]");
	    
	    /**
	     * 将提供的异常对象包装成运行时异常，并重设置异常对象概念信息 
	     * @param e 任何异常地象
	     * @param fmt 格式化的字符串
	     * @param args 格式化参数
	     * @return
	     */
	    public static RuntimeException wrapThrow(Exception e, String fmt, Object... args) {

	        return new RuntimeException(String.format(fmt, args), e);
	    }
	    
	}
