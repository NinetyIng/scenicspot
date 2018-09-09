package com.ythd.ower.task.mapper;

import com.ythd.ower.common.dto.PageData;
import com.ythd.ower.task.model.TimerTaskLogModel;
import com.ythd.ower.task.model.TimerTaskModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by junbo
 * on 2018/9/8
 */
public interface TimerTaskMapper {

    /**
     * 根据条件查询任务
     * @param pd
     * @return
     */
    List<TimerTaskModel> findByCondition(PageData pd);

    /**
     * 插入任务日志
     * @param logModel
     */
    void insertLog(TimerTaskLogModel logModel);


    /**
     * 删除任务
     * @param name
     * @param goupName
     */
    void deleteByNameAndGroup(@Param("name") String name,@Param("groupName") String goupName);

    /**
     * 添加任务
     * @param model
     */
    void addJob(TimerTaskModel model);

    /**
     * 更改数据库任务
     * @param model
     */
    void updateJob(TimerTaskModel model);


}
