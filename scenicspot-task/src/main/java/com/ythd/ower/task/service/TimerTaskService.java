package com.ythd.ower.task.service;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.task.mapper.TimerTaskMapper;
import com.ythd.ower.task.model.TimerTaskLogModel;
import com.ythd.ower.task.model.TimerTaskModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by junbo
 * on 2018/9/8
 */
@Service
public class TimerTaskService {

    @Resource
    private TimerTaskMapper timerTaskMapper;


    public List<TimerTaskModel> findByCondition(PageData pd){
        return timerTaskMapper.findByCondition(pd);
    }

    public void insertLog(TimerTaskLogModel logModel){
        timerTaskMapper.insertLog(logModel);
    }

    public void deleteJob(TimerTaskModel model){
        timerTaskMapper.deleteByNameAndGroup(model.getName(),model.getGroupName());
    }

    public void updateJob(TimerTaskModel model){
        timerTaskMapper.updateJob(model);
    }

    public void addJob(TimerTaskModel model){
        timerTaskMapper.addJob(model);
    }
}
