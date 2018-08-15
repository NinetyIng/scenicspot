package com.ythd.ower.common.ibox;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class JavaBeanDescriptor  implements Serializable, Iterable<Map.Entry<Object, Object>>{

    private static final long serialVersionUID = -8505586483570518029L;

    public static final int TYPE_CLASS = 1;

    public static final int TYPE_ENUM = 2;

    public static final int TYPE_COLLECTION = 3;

    public static final int TYPE_MAP = 4;

    public static final int TYPE_ARRAY = 5;

    public static final int TYPE_PRIMITIVE = 6;

    public static final int TYPE_BEAN = 7;

    private static final String ENUM_PROPERTY_NAME = "name";

    private static final String CLASS_PROPERTY_NAME = "name";

    private static final String PRIMITIVE_PROPERTY_VALUE = "value";

    /**
     * Used to define a type is valid.
     * @see #isValidType(int)
     */
    private static final int TYPE_MAX = TYPE_BEAN;

    /**
     * Used to define a type is valid.
     * @see #isValidType(int)
     */
    private static final int TYPE_MIN = TYPE_CLASS;

    private String className;

    private int type;

    private Map<Object, Object> properties = new LinkedHashMap<Object, Object>();

    public JavaBeanDescriptor() {}

    public JavaBeanDescriptor(String className, int type) {
        notEmpty(className, "class name is empty");
        if (!isValidType(type)) {
            throw new IllegalArgumentException(
                    new StringBuilder(16).append("type [ ")
                            .append(type).append(" ] is unsupported").toString());
        }

        this.className = className;
        this.type = type;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isClassType() {
        return TYPE_CLASS == type;
    }

    public boolean isEnumType() {
        return TYPE_ENUM == type;
    }

    public boolean isCollectionType() {
        return TYPE_COLLECTION == type;
    }

    public boolean isMapType() {
        return TYPE_MAP == type;
    }

    public boolean isArrayType() {
        return TYPE_ARRAY == type;
    }

    public boolean isPrimitiveType() {
        return TYPE_PRIMITIVE == type;
    }

    public boolean isBeanType() {
        return TYPE_BEAN == type;
    }

    public int getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public Object setProperty(Object propertyName, Object propertyValue) {
        notNull(propertyName, "Property name is null");

        Object oldValue = properties.put(propertyName, propertyValue);
        return oldValue;
    }

    public String setEnumNameProperty(String name) {
        if (isEnumType()) {
            Object result = setProperty(ENUM_PROPERTY_NAME, name);
            return result == null ? null : result.toString();
        }
        throw new IllegalStateException("The instance is not a enum wrapper");
    }

    public String getEnumPropertyName() {
        if (isEnumType()) {
            Object result = getProperty(ENUM_PROPERTY_NAME).toString();
            return result == null ? null : result.toString();
        }
        throw new IllegalStateException("The instance is not a enum wrapper");
    }

    public String setClassNameProperty(String name) {
        if (isClassType()) {
            Object result = setProperty(CLASS_PROPERTY_NAME, name);
            return result == null ? null : result.toString();
        }
        throw new IllegalStateException("The instance is not a class wrapper");
    }

    public String getClassNameProperty() {
        if (isClassType()) {
            Object result = getProperty(CLASS_PROPERTY_NAME);
            return result == null ? null : result.toString();
        }
        throw new IllegalStateException("The instance is not a class wrapper");
    }

    public Object setPrimitiveProperty(Object primitiveValue) {
        if (isPrimitiveType()) {
            return setProperty(PRIMITIVE_PROPERTY_VALUE, primitiveValue);
        }
        throw new IllegalStateException("The instance is not a primitive type wrapper");
    }

    public Object getPrimitiveProperty() {
        if (isPrimitiveType()) {
            return getProperty(PRIMITIVE_PROPERTY_VALUE);
        }
        throw new IllegalStateException("The instance is not a primitive type wrapper");
    }

    public Object getProperty(Object propertyName) {
        notNull(propertyName, "Property name is null");
        Object propertyValue = properties.get(propertyName);
        return propertyValue;
    }

    public boolean containsProperty(Object propertyName) {
        notNull(propertyName, "Property name is null");
        return properties.containsKey(propertyName);
    }

    public Iterator<Map.Entry<Object, Object>> iterator() {
        return properties.entrySet().iterator();
    }

    public int propertySize() {
        return properties.size();
    }

    private boolean isValidType(int type) {
        return TYPE_MIN <= type && type <= TYPE_MAX;
    }

    private void notNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private void notEmpty(String string, String message) {
        if (isEmpty(string)) {
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }
}
