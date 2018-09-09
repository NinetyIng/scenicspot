package com.ythd.ower.task.utils;

import com.ythd.ower.common.tools.TimeUtils;
import com.ythd.ower.task.job.QuartzManager;
import com.ythd.ower.task.model.TimerTaskLogModel;
import com.ythd.ower.task.model.TimerTaskModel;
import com.ythd.ower.task.service.TimerTaskService;
import com.ythd.ower.task.spring.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by junbo
 * on 2018/9/8
 */
public class TaskUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(TaskUtils.class);
    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    @SuppressWarnings("unchecked")
    public static void invokMethod(TimerTaskModel scheduleJob) {
        Object object = null;
        Class clazz = null;
        boolean flag = true;
        TimerTaskService timerTaskService = SpringUtils.getBean(TimerTaskService.class);
        QuartzManager quartzManager = SpringUtils.getBean(QuartzManager.class);

        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringUtils.getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanName())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanName());
                object = clazz.newInstance();
            } catch (Exception e) {
                flag = false;
                TimerTaskLogModel tlog = new TimerTaskLogModel()
                        .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                        .setJobId(scheduleJob.getId().toString())
                        .setReason("未找到"+scheduleJob.getBeanName()+"对应的class").setStatus("fail");
                timerTaskService.insertLog(tlog);
                e.printStackTrace();
            }
        }
        if (object == null) {
            flag = false;
            TimerTaskLogModel tlog = new TimerTaskLogModel()
                    .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                    .setJobId(scheduleJob.getId().toString())
                    .setReason("未找到"+scheduleJob.getBeanName()+"对应的class").setStatus("fail");
            timerTaskService.insertLog(tlog);
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), new Class[] { String.class });
        } catch (NoSuchMethodException e) {
            flag = false;
            LOGGER.error("任务名称 = [{}]---------------未启动成功，方法名设置错误！！！",  scheduleJob.getName());
            TimerTaskLogModel tlog = new TimerTaskLogModel()
                    .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                    .setJobId(scheduleJob.getId().toString())
                    .setReason("未找到"+scheduleJob.getBeanName()+"类下"+scheduleJob.getMethodName()+"对应的方法").setStatus("fail");
            timerTaskService.insertLog(tlog);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object, scheduleJob.getJobData());
            } catch (IllegalAccessException e) {
                flag = false;
                TimerTaskLogModel tlog = new TimerTaskLogModel()
                        .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                        .setJobId(scheduleJob.getId().toString())
                        .setReason("未找到"+scheduleJob.getBeanName()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误").setStatus("fail");
                timerTaskService.insertLog(tlog);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                flag = false;
                TimerTaskLogModel tlog = new TimerTaskLogModel()
                        .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                        .setJobId(scheduleJob.getId().toString())
                        .setReason("未找到"+scheduleJob.getBeanName()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误").setStatus("fail");
                timerTaskService.insertLog(tlog);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                flag = false;
                TimerTaskLogModel tlog = new TimerTaskLogModel()
                        .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                        .setJobId(scheduleJob.getId().toString())
                        .setReason("未找到"+scheduleJob.getBeanName()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误").setStatus("fail");
                timerTaskService.insertLog(tlog);
                e.printStackTrace();
            }
        }
        if(flag){
            LOGGER.info("任务名称 = [ "+ scheduleJob.getName() + "]----------启动成功");
            TimerTaskLogModel tlog = new TimerTaskLogModel()
                    .setCreateTime(TimeUtils.toStringFormat_1(new Date()))
                    .setJobId(scheduleJob.getId().toString())
                    .setStatus("success");
            timerTaskService.insertLog(tlog);
        }
        //执行之后 删除当前任务
        quartzManager.removeJob(scheduleJob);

    }
}
