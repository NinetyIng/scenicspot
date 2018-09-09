package com.ythd.ower.task.job;

import com.ythd.ower.task.constant.TimerTaskConstant;
import com.ythd.ower.task.model.TimerTaskModel;
import com.ythd.ower.task.utils.TaskUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by junbo
 * on 2018/9/8
 */
public class QuartzJobFactory implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        TimerTaskModel scheduleJob = (TimerTaskModel) context.getMergedJobDataMap().get(TimerTaskConstant.JOB_NAME);
        TaskUtils.invokMethod(scheduleJob);
    }
}
