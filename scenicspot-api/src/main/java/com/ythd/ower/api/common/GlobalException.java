package com.ythd.ower.api.common;

import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.dto.ErrorCode;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.tools.MapperUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ResponseBody
    @ExceptionHandler({BizServiceException.class})
     public String bizException(Exception e) {
        BizServiceException biz = (BizServiceException)e;
       return MapperUtil.toJson(DtoUtils.getFailResponse(new ErrorCode(biz.getErrorCode(),biz.getErrorDesc())));
     }
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public String exception(Exception e) {
        e.printStackTrace();
        return MapperUtil.toJson(DtoUtils.getFailResponse(ErrorCodesContants.SYSTEM_ERROR));
    }

}
