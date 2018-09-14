package com.ythd.ower.task.job;

import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.common.tools.TimeUtils;
import com.ythd.ower.task.constant.TimerTaskConstant;
import com.ythd.ower.task.model.TimerTaskModel;
import com.ythd.ower.task.service.TimerTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by junbo
 * on 2018/9/8
 */
@Service
public class QuartzManager {

    @Autowired
    public  SchedulerFactoryBean schedulerFactoryBean;


    @Autowired
    private TimerTaskService timerTaskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzManager.class);


    public void addJob(TimerTaskModel job){
        try {
            /**
             * 数据库添加job
             */
            timerTaskService.addJob(job);

            LOGGER.info("向定时任务中动态添加一个任务，任务信息{}", MapperUtil.toJson(job));
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            LOGGER.debug(scheduler + "...........................................add");
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroupName());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger != null){
                LOGGER.error("任务已存在，{}",triggerKey.getName());
                return;
            }
            Class clazz = job.getIsRunning() ? QuartzJobFactory.class
                    : QuartzJobFactoryDisallowConcurrentExecution.class;
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getName(), job.getGroupName()).usingJobData("data", job.getJobData()).build();
            jobDetail.getJobDataMap().put(TimerTaskConstant.JOB_NAME, job);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());
            trigger = TriggerBuilder.newTrigger().withDescription(job.getId().toString()).withIdentity(job.getName(), job.getGroupName())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }catch (Exception e){
            LOGGER.error("动态添加任务失败，失败信息",e);
        }
    }

    public  void removeJob(TimerTaskModel job){
        try {
            /**
             * 数据库更新job
             */
            LOGGER.info("更新数据库任务状态");
            TimerTaskModel updateModel = new TimerTaskModel();
            updateModel.setId(job.getId());
            updateModel.setEndTime(TimeUtils.toStringFormat_1(new Date()));
            updateModel.setJobStatus(TimerTaskModel.STATUS_NOT_RUNNING);
            updateModel.setPlanStatus(TimerTaskModel.STATUS_NOT_RUNNING);
            updateModel.setModifyTime(TimeUtils.toStringFormat_1(new Date()));
            timerTaskService.updateJob(job);
            JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroupName());
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.deleteJob(jobKey);
            LOGGER.info("动态移除key成功");
        }catch (Exception e){
            LOGGER.error("动态删除任务失败，失败信息",e);
        }
    }
}
