package com.ythd.ower.common.dto;

import lombok.Data;

@Data
public class ErrorCode {

    private String code;
    private String desc;
    public ErrorCode(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

}
