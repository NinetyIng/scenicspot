package com.ythd.ower.common.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class MapperUtil {
    public MapperUtil() {
    }
    public static String toJson(Object obj) {
        return toJson(obj, true, (SerializeFilter)null);
    }

    public static String toJson(Object obj, boolean excludeNull, SerializeFilter filter) {
        if (obj == null) {
            return "";
        } else if (excludeNull) {
            return filter != null ? JSONObject.toJSONString(obj, filter, new SerializerFeature[]{SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField}) : JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField});
        } else {
            return filter != null ? JSONObject.toJSONString(obj, filter, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField}) : JSONObject.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.IgnoreNonFieldGetter, SerializerFeature.SortField});
        }
    }

    public static String toJson(Object object, SerializeFilter filter) {
        return toJson(object, true, filter);
    }

    public static <T> T map(String jsonString, Class<T> clazz) {
        return StringUtils.isBlank(jsonString) ? null : JSON.parseObject(jsonString, clazz);
    }

    public static <T> List<T> mapToList(String jsonString, Class<T> clazz) {
        return StringUtils.isBlank(jsonString) ? null : JSONArray.parseArray(jsonString, clazz);
    }

    public static <T> Map<String, T> toMap(String jsonString) {
        return StringUtils.isBlank(jsonString) ? null : (Map)JSON.parseObject(jsonString, new TypeReference<Map<String, T>>() {
        }, new Feature[0]);
    }

    public static <T> Map<String, T> beanToMap(Object object) {
        if (object == null) {
            return null;
        } else {
            String jsonString = toJson(object);
            return toMap(jsonString);
        }
    }

    public static final String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter();

        String var10;
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            SerializerFeature[] var8 = features;
            int var7 = features.length;

            int var6;
            for(var6 = 0; var6 < var7; ++var6) {
                SerializerFeature feature = var8[var6];
                serializer.config(feature, true);
            }

            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            if (filters != null) {
                SerializeFilter[] var14 = filters;
                var7 = filters.length;

                for(var6 = 0; var6 < var7; ++var6) {
                    SerializeFilter filter = var14[var6];
                    if (filter instanceof PropertyPreFilter) {
                        serializer.getPropertyPreFilters().add((PropertyPreFilter)filter);
                    }

                    if (filter instanceof NameFilter) {
                        serializer.getNameFilters().add((NameFilter)filter);
                    }

                    if (filter instanceof ValueFilter) {
                        serializer.getValueFilters().add((ValueFilter)filter);
                    }

                    if (filter instanceof PropertyFilter) {
                        serializer.getPropertyFilters().add((PropertyFilter)filter);
                    }

                    if (filter instanceof BeforeFilter) {
                        serializer.getBeforeFilters().add((BeforeFilter)filter);
                    }
                }
            }

            serializer.write(object);
            var10 = out.toString();
        } finally {
            out.close();
        }

        return var10;
    }
}
