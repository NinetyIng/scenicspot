package com.ythd.ower.common.ibox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;

public class GenericResponseDto extends HashMap<String, Object> {
    private static final long serialVersionUID = 870714850748751859L;
    public static final String RESPONSE_OK = "1";

    public GenericResponseDto() {
    }

    public void setResultCode(String resultCode) {
        this.put("resultCode", resultCode);
    }

    public String getResultCode() {
        return this.getStringData("resultCode");
    }

    public void setErrorCode(String errorCode) {
        this.put("errorCode", errorCode);
    }

    public String getErrorCode() {
        return this.getStringData("errorCode");
    }

    public void setErrorDesc(String errorDesc) {
        this.put("errorDesc", errorDesc);
    }

    public String getErrorDesc() {
        return this.getStringData("errorDesc");
    }

    public Object getData(String key) {
        return this.get(key);
    }

    public String getStringData(String key) {
        Object obj = this.getData(key);
        if (null != obj) {
            if (obj instanceof String) {
                return (String)obj;
            } else if (obj instanceof Integer) {
                Integer data = (Integer)obj;
                return String.valueOf(data);
            } else if (obj instanceof Long) {
                Long data = (Long)obj;
                return String.valueOf(data);
            } else {
                return (String)obj;
            }
        } else {
            return null;
        }
    }

    public Long getLongData(String key) {
        Object obj = this.getData(key);
        if (null != obj) {
            if (obj instanceof String) {
                String data = (String)obj;
                return Long.valueOf(data);
            } else if (obj instanceof Integer) {
                Integer data = (Integer)obj;
                return (long)data;
            } else {
                return obj instanceof Long ? (Long)obj : (Long)obj;
            }
        } else {
            return null;
        }
    }

    public Integer getIntegerData(String key) {
        Object obj = this.getData(key);
        if (null != obj) {
            if (obj instanceof String) {
                String data = (String)obj;
                return Integer.valueOf(data);
            } else {
                return obj instanceof Integer ? (Integer)obj : (Integer)obj;
            }
        } else {
            return null;
        }
    }

    public <T> T parseData(String key, Class<T> clazz) {
        Object obj = this.get(key);
        if(obj instanceof JSONObject){
            return JSON.toJavaObject((JSONObject)obj, clazz);
        }
        return null;
    }

    public List<String> getStringList(String key) {
        return (List)this.getData(key);
    }

    public Object putData(String key, Object value) {
        return this.put(key, value);
    }

    public boolean isOk() {
        return !this.isEmpty() && "1".equals(this.getResultCode());
    }

    public void setSuccess() {
        this.setResultCode("1");
    }

    public String toString() {
        try {
            return JSON.toJSONString(this);
        } catch (Exception var2) {
            return null;
        }
    }
}
