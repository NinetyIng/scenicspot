package com.ythd.ower.b2c.task;

import com.alibaba.fastjson.JSONObject;
import com.ythd.ower.b2c.constant.OrderStatus;
import com.ythd.ower.b2c.mapper.AppProductOrderMapper;
import com.ythd.ower.b2c.model.ProductOrderModel;
import com.ythd.ower.common.tools.MapperUtil;
import com.ythd.ower.task.constant.TimerTaskConstant;
import com.ythd.ower.task.job.QuartzManager;
import com.ythd.ower.task.model.TimerTaskModel;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description: 定时任务执行计划类  - 更改下单之后n分钟之后未结账的订单为取消状态
 * @author: liujunbo
 * @since: 2018/9/10
 * @version: $Revision$
 */
@Component
public class CancleOrderExcutor {

  private static final Logger LOGGER = LoggerFactory.getLogger(CancleOrderExcutor.class);

  @Resource
  private AppProductOrderMapper appProductOrderMapper;

  @Resource
  private QuartzManager quartzManager;

  public void init(String excutorParam){
    ProductOrderModel orderModel = JSONObject.parseObject(excutorParam, ProductOrderModel.class);
    orderModel.setOrderStatus(OrderStatus.YQX.getCode() + StringUtils.EMPTY);
    appProductOrderMapper.cancleOrder(orderModel);
    LOGGER.error("任务执行完毕，即将移除任务，任务信息{}", MapperUtil.toJson(orderModel));
    TimerTaskModel timerTaskModel = new TimerTaskModel();
    timerTaskModel.setName(orderModel.getOrderSn());
    timerTaskModel.setGroupName(TimerTaskConstant.GROUP_NAME_ORDER);
    quartzManager.removeJob(timerTaskModel);
  }

}
