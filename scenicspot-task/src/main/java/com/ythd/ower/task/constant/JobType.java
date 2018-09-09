package com.ythd.ower.task.constant;

/**
 * Created by junbo
 * on 2018/9/8
 */
public enum  JobType {

    NOPAY_ORDER_JOB("1","未支付订单任务");

    private String code;

    private String desc;

    JobType(String code,String desc){
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
