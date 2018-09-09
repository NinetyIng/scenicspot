package com.ythd.ower.task.init;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.task.constant.TimerTaskConstant;
import com.ythd.ower.task.job.QuartzJobFactory;
import com.ythd.ower.task.job.QuartzJobFactoryDisallowConcurrentExecution;
import com.ythd.ower.task.model.TimerTaskModel;
import com.ythd.ower.task.service.TimerTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import java.util.List;

/**
 * Created by junbo
 * on 2018/9/8
 */
public class InitQuartzJob implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(InitQuartzJob.class);


    private static ApplicationContext appCtx;


    public static SchedulerFactoryBean schedulerFactoryBean = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.appCtx == null) {
            this.appCtx = applicationContext;
        }
    }


    public static void init() {
        schedulerFactoryBean =  appCtx.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            logger.info(scheduler.getSchedulerName());
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        // 这里从数据库中获取任务信息数据
        TimerTaskService timerTaskService = appCtx.getBean(TimerTaskService.class);
        PageData condition = new PageData();
        condition.put(TimerTaskConstant.PLAN_STATUS,TimerTaskConstant.OPENED);
        List<TimerTaskModel> taskList = timerTaskService.findByCondition(condition);
        for (TimerTaskModel job : taskList) {
            try {
                addJob(job);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    public static void addJob(TimerTaskModel job) throws SchedulerException {
        if (job == null || !TimerTaskModel.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        logger.debug(scheduler + "...........................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroupName());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = job.getIsRunning() ? QuartzJobFactory.class
                    : QuartzJobFactoryDisallowConcurrentExecution.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getName(), job.getGroupName()).usingJobData("data", job.getJobData()).build();

            jobDetail.getJobDataMap().put(TimerTaskConstant.JOB_NAME, job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

            trigger = TriggerBuilder.newTrigger().withDescription(job.getId().toString()).withIdentity(job.getName(), job.getGroupName())
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).usingJobData("data", job.getJobData()).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

}
