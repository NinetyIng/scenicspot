package com.ythd.ower.common.constants;

public enum  JavaBeanAccessor {
    /** Field accessor. */
    FIELD,
    /** Method accessor.*/
    METHOD,
    /** Method prefer to field. */
    ALL;

    public static boolean isAccessByMethod(JavaBeanAccessor accessor) {
        return METHOD.equals(accessor) || ALL.equals(accessor);
    }

    public static boolean isAccessByField(JavaBeanAccessor accessor) {
        return FIELD.equals(accessor) || ALL.equals(accessor);
    }
}
