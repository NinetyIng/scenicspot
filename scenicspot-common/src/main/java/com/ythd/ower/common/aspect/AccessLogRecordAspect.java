/*
 * Copyright (C) 2011-2018 ShenZhen IBOXCHAIN Information Technology Co.,Ltd.
 * All right reserved.
 * This software is the confidential and proprietary
 * information of IBOXCHAIN Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with IBOXCHAIN inc.
 *
 */

package com.ythd.ower.common.aspect;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ythd.ower.common.constants.LogConstants;
import com.ythd.ower.common.constants.RespcdContants;
import com.ythd.ower.common.constants.SpecificSymbolConstants;
import com.ythd.ower.common.ibox.DtoUtils;
import com.ythd.ower.common.ibox.GenericResponseDto;
import com.ythd.ower.common.reflex.ReflectHelper;
import com.ythd.ower.common.tools.*;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * add interface log
 * @author zhuqianchao 
 * add access Logger
 * @author xuyanxiong
 * @since 2017/12/19
 *
 */
public class AccessLogRecordAspect {
	
    private static final Logger logger = LoggerFactory.getLogger(LogConstants.HTTP_INTERFACE_LOG);
    private static final Logger accessLogger = LoggerFactory.getLogger(LogConstants.HTTP_ACCESS_LOG);

    public Object handleApi(ProceedingJoinPoint pjp) {
        String targetName = null;
        String methodName = null;
        String serviceId="";
        try {

            MDC.put("transactionId", String.valueOf((new TransactionIdUtil(2L)).nextId()));
            targetName = pjp.getTarget().getClass().getSimpleName();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            methodName = signature.getMethod().getName();
            serviceId=StringUtils.defaultIfEmpty(targetName, StringUtils.EMPTY)+SpecificSymbolConstants.UNDERLINE+StringUtils.defaultIfEmpty(methodName, StringUtils.EMPTY);
            Object[] args = pjp.getArgs();
            Object param = null;
            if (args != null && args.length > 0) {
                param = args[0];
            }

            logger.info("[{}] [HTTP] [request] - {}", serviceId, MapperUtil.toJson(param));
            long startTime = System.currentTimeMillis();
            Object result = pjp.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("[{}] [HTTP] [response] - {}", serviceId, MapperUtil.toJson(result));
            
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
 
            Map<String, String> accessMap = getAccessMap(result, serviceId, startTime, endTime);

            InetAddress addr=null;
            String remoteIp="";
			try {
				addr = InetAddress.getLocalHost();
				remoteIp= WebUtil.getIpAddr(request);
			} catch (UnknownHostException e) {
	            logger.error("[{}] [HTTP] [request] - Exception:{}", serviceId, e.getMessage());
			} catch(Exception e2) {
				logger.error("[{}] [HTTP] [request] - Exception:{}", serviceId, e2.getMessage());
			}
			
            String localIp=addr==null?"":addr.getHostAddress() + SpecificSymbolConstants.COLON +request.getServerPort();
             
            accessMap.put(LogConstants.REMOTE_IP, remoteIp);
            accessMap.put(LogConstants.LOCAL_IP, localIp);
            accessMap.put(LogConstants.MODULE, LogConstants.MODULE_NAME);
            accessLogger.info(MapperUtil.toJson(accessMap));
            
            return result;
        } catch (Throwable throwable) {
            logger.error("[{}] [HTTP] [request] - Exception:{}", serviceId, throwable.getMessage());
            return DtoUtils.getFailResponse(RespcdContants.YTHD_MT0001);
        }
    }



	private Map<String, String> getAccessMap(Object result, String serviceId, long startTime, long endTime) {
		
	       Map<String, String> accessMap = new HashMap<String, String>(6);

	        String transactionIdMdc = MDC.get("transactionId");
	        if (!StringUtils.isBlank(transactionIdMdc)) {
	            accessMap.put("transactionId", transactionIdMdc);
	        }
	        accessMap.put("serviceId", serviceId);
	        accessMap.put("timeDiff", String.valueOf(endTime - startTime));
	        accessMap.put("startTime", TimeUtils.toStringFormat_1(new Date(startTime)));
	        accessMap.put("endTime", TimeUtils.toStringFormat_1(new Date(endTime)));
	        if (result == null) {
	        	return accessMap;
	        }
	        GenericResponseDto respDto = ReflectHelper.getSerializeObject(result);
	        accessMap.put("resultCode", respDto.getResultCode());
	        accessMap.put("resultCode", respDto.getErrorCode());
	        return accessMap;

	}


}
