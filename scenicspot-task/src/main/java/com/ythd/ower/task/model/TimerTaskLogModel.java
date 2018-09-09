package com.ythd.ower.task.model;

/**
 * Created by junbo
 * on 2018/9/8
 */
public class TimerTaskLogModel {

    private Integer id;

    private String createTime;

    private String jobId;

    private String reason;

    private String status;

    public Integer getId() {
        return id;
    }

    public TimerTaskLogModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public TimerTaskLogModel setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getJobId() {
        return jobId;
    }

    public TimerTaskLogModel setJobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public TimerTaskLogModel setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TimerTaskLogModel setStatus(String status) {
        this.status = status;
        return this;
    }
}
