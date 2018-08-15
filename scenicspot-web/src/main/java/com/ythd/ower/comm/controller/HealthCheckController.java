package com.ythd.ower.comm.controller;

import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericRequestDto;
import com.ythd.ower.common.ibox.GenericResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class HealthCheckController.
 *
 * Description:
 *
 * @author: liujunbo
 * @since: 2018/7/21
 * @version: 1.0.0
 */
@RequestMapping("/")
@RestController
public class HealthCheckController {

    @RequestMapping("healthCheck")
    public GenericResponseDto healthCheck(){
        return DtoUtils.getSuccessResponse();
    }
}
