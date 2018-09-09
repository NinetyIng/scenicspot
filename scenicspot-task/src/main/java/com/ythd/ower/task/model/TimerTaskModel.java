package com.ythd.ower.task.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by junbo
 * on 2018/9/8
 */
@Data
public class TimerTaskModel {

    public static final String STATUS_RUNNING = "1";  //正在运行


    public static final String STATUS_NOT_RUNNING = "0"; // 已停止


    public static final String CONCURRENT_IS = "1";


    public static final String CONCURRENT_NOT = "0";


    private Integer id;

    private String name;

    private String jobType;

    private String groupName;

    private String startTime;

    private String endTime;

    private String cron;

    private String jobStatus;

    private String planStatus;

    private Boolean isRunning;

    private String jobData;

    private String methodName;

    private String beanName;

    private String description;

    private String createId;

    private String createTime;

    private String modifyId;

    private String modifyTime;

    private String springId;


}
