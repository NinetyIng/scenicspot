package com.ythd.ower.manager.controller;

import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/")
@RestController
public class HealthCheckController {
    @RequestMapping("healthCheck.do")
    public GenericResponseDto healthCheck(){
        return DtoUtils.getSuccessResponse();
    }
}