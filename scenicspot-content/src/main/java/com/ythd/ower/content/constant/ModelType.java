package com.ythd.ower.content.constant;


public enum  ModelType {
    SINGLE("0","单图模式"),COMPLEX("1","多图模式");
    private String code;
    private String desc;
    ModelType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
