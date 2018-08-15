package com.ythd.ower.common.ibox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GenericRequestDto extends HashMap<String, Object>{

    private static final long serialVersionUID = -6810272545220742760L;
    private String transactionId;
    private transient Map<String, Object> innerMap = new HashMap();
    private GenericSessionContext session;

    public GenericRequestDto() {
    }

    public String getServiceId() {
        return this.getStringData("serviceId");
    }

    public void setServiceId(String serviceId) {
        this.put("serviceId", serviceId);
    }

    public Map<String, Object> getInnerMap() {
        return this.innerMap;
    }

    public void setInnerMap(Map<String, Object> innerMap) {
        this.innerMap = innerMap;
    }

    public Object getInnerParameter(String key) {
        return this.innerMap.get(key);
    }

    public String getStringInnerParameter(String key) {
        return (String)this.getInnerParameter(key);
    }

    public void putInnerParameter(String key, Object value) {
        this.innerMap.put(key, value);
    }

    public GenericRequestHeader getRequestHeader() {
        return (GenericRequestHeader)this.get("requestHeader");
    }

    public void setRequestHeader(GenericRequestHeader requestHeader) {
        this.put("requestHeader", requestHeader);
    }

    public void removeData(String key) {
        this.remove(key);
    }

    public Object getData(String key) {
        return this.get(key);
    }

    public void putData(String key, Object value) {
        this.put(key, value);
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

    public GenericSessionContext getSession() {
        if (this.session == null) {
            this.session = new GenericSessionContext();
        }

        return this.session;
    }

    public void setSession(GenericSessionContext session) {
        this.session = session;
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

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

 /*   public <T> T parseData(String key, Class<T> clazz) {
        Object obj = this.get(key);
        return obj instanceof JSONObject ? JSON.toJavaObject((JSONObject)obj, clazz) : obj;
    }*/

    public GenericRequestDto clone() {
        GenericRequestDto requestObject = null;
        requestObject = (GenericRequestDto)super.clone();
        return requestObject;
    }

    public String toString() {
        try {
            return JSON.toJSONString(this);
        } catch (Exception var2) {
            return null;
        }
    }
}
