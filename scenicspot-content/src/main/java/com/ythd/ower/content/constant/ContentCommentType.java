package com.ythd.ower.content.constant;

public enum  ContentCommentType {

    UNAUDITED("0","未审核"),AUDITED("1","已通过审核"),NOPASS("2","未通过审核");
    private String code;
    private String desc;
    ContentCommentType(String code,String desc){
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
