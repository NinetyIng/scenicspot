package com.ythd.ower.common.exception;

import com.ythd.ower.common.dto.ErrorCode;
import lombok.Data;

@Data
public class BizServiceException extends RuntimeException {
    private String errorCode;
    private String errorDesc;

    public BizServiceException(ErrorCode errorCode){
        super(errorCode.getCode());
        this.errorCode = errorCode.getCode();
        this.errorDesc = errorCode.getDesc();
    }
}
