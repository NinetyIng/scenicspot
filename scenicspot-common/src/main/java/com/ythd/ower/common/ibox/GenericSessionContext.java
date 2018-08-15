package com.ythd.ower.common.ibox;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GenericSessionContext {
    private static final long serialVersionUID = 3476626729518233777L;
    private static String SESSION_ATTRIBUTE_UPDATE = "U";
    private static String SESSION_ATTRIBUTE_REMOVE = "R";
    private Map<String, Object> ctxMap;
    private Map<String, String> transitionMap;

    public GenericSessionContext() {
    }

    public Map<String, Object> getCtxMap() {
        return this.ctxMap;
    }

    public void setCtxMap(Map<String, Object> ctxMap) {
        this.ctxMap = ctxMap;
    }

    public Map<String, String> getTransitionMap() {
        return this.transitionMap;
    }

    public void setTransitionMap(Map<String, String> transitionMap) {
        this.transitionMap = transitionMap;
    }

    public Object getData(String key) {
        if (this.ctxMap == null) {
            this.ctxMap = new HashMap();
        }

        return this.ctxMap.get(key);
    }

    public void putData(String key, Object data) {
        if (this.ctxMap == null) {
            this.ctxMap = new HashMap();
        }

        this.ctxMap.put(key, data);
        this.addTransition(key, SESSION_ATTRIBUTE_UPDATE);
    }

    public Object removeData(String key) {
        this.addTransition(key, SESSION_ATTRIBUTE_REMOVE);
        return this.ctxMap != null && !this.ctxMap.isEmpty() ? this.ctxMap.remove(key) : null;
    }

    public void putAll(Map<String, Object> ctxMap) {
        if (this.ctxMap == null) {
            this.ctxMap = new HashMap();
        }

        this.ctxMap.putAll(ctxMap);
    }

    public String getTransitionStatus(String key) {
        return this.transitionMap == null ? null : (String)this.transitionMap.get(key);
    }

    private void addTransition(String key, String value) {
        if (this.transitionMap == null) {
            this.transitionMap = new HashMap();
        }

        this.transitionMap.put(key, value);
    }

    /*public <T> T parseData(String key, Class<T> clazz) {
        Object obj = this.ctxMap.get(key);
        return obj instanceof JSONObject ? JSON.toJavaObject((JSONObject)obj, clazz) : obj;
    }*/

    public String toString() {
        return "GenericSessionContext [ctxMap=" + this.ctxMap + ", transitionMap=" + this.transitionMap + "]";
    }
}
