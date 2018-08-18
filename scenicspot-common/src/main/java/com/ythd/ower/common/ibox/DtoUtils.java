package com.ythd.ower.common.ibox;

import com.ythd.ower.common.dto.ErrorCode;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DtoUtils {

    public static <T> T mapToBean(Map<String, Object> map, Class<T> c) {
        try {
            if (map == null || map.isEmpty()) {
                return null;
            }
            Field[] fields = c.getDeclaredFields();
            if (fields == null || fields.length <= 0) {
                return null;
            }
            List<String> fieldNames = new ArrayList<>();
            for (Field field : fields) {
                fieldNames.add(field.getName());
            }
            T t = c.newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (fieldNames.contains(entry.getKey())) {
                    BeanUtils.setProperty(t, entry.getKey(), entry.getValue());
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getReqestDto(GenericRequestDto request, Class<T> c) {
        return request == null ? null : mapToBean(request, c);
    }

    public static GenericResponseDto getSuccessResponse() {
        return getSuccessResponse(null);
    }

    public static GenericResponseDto getSuccessResponse(Map<String, Object> map) {
        GenericResponseDto resp = getDefaultResponse();
        resp.setSuccess();
        if (map != null) {
            resp.putAll(map);
        }
        return resp;
    }

    public static GenericResponseDto getFailResponse(String errorCode) {
        return getFailResponse(errorCode, null);
    }

    public static GenericResponseDto getFailResponse(ErrorCode errorCode) {
        GenericResponseDto resp = getDefaultResponse();
        resp.setErrorCode(errorCode.getCode());
        resp.setErrorDesc(errorCode.getDesc());
        return resp;
    }


    public static GenericResponseDto getFailResponse(String errorCode, boolean isNeedValid) {
        GenericResponseDto resp = getDefaultResponse();
        resp.setErrorCode(errorCode);
        if (isNeedValid) {
            resp.put("needValidCode", "1");
        }
        return resp;
    }

    public static GenericResponseDto getFailResponse(String errorCode, Map<String, Object> map) {
        GenericResponseDto resp = getDefaultResponse();
        resp.setErrorCode(errorCode);
        resp.setErrorDesc(MsgConfig.get(errorCode));
        if (map != null) {
            resp.putAll(map);
        }
        return resp;
    }

    public static <T> GenericResponseDto getFailResponse(String errorCode, T data) {
        GenericResponseDto resp = getDefaultResponse();
        resp.setErrorCode(errorCode);
        resp.setErrorDesc(MsgConfig.get(errorCode));
        resp.putData("data", data);
        return resp;
    }

    private static GenericResponseDto getDefaultResponse() {
        GenericResponseDto resp = new GenericResponseDto();
        resp.setResultCode("0");
        return resp;
    }
}
