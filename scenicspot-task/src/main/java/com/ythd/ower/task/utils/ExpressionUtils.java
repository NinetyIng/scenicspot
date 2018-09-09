package com.ythd.ower.task.utils;

import com.ythd.ower.common.constants.ErrorCodesContants;
import com.ythd.ower.common.exception.BizServiceException;
import com.ythd.ower.common.tools.TimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by junbo
 * on 2018/9/8
 */
public class ExpressionUtils {

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public enum CronExpressionType{
      SECOND,MINUTE,HOUR,DAY,MOUTH,WEEK,YEAR
    }
    /**
     * 构建 Quartz 表达式
     * @return
     */
    public static String  bulidTimeCron(String startTime,CronExpressionType type,Integer interval){

       String[]  expressAry = new String[]{"*","*","*","*","*","*","?"};
       Long diffTime =  TimeUtils.diff(TimeUtils.stringToDate(startTime,TIME_FORMAT),new Date()) / 1000;
       Long diff = 0L;

       if (type.name().equals(CronExpressionType.SECOND.name())){
           diff = Long.valueOf(interval) - diffTime;
       }
        if (type.name().equals(CronExpressionType.MINUTE.name())){
            diff = Long.valueOf(interval) - diffTime / 60;
        }
        if (type.name().equals(CronExpressionType.HOUR.name())){
            diff = Long.valueOf(interval) - diffTime / 3600;
        }

        if (type.name().equals(CronExpressionType.DAY.name())){
            diff = Long.valueOf(interval) - diffTime / (3600*24);
        }
        if(diff < 0){
           throw new BizServiceException(ErrorCodesContants.APP_CONFIG_ERROR);
        }
        expressAry[type.ordinal()] = "0/"+ diff;
        for (int i = 0;i< type.ordinal();i++){
            expressAry[i] = "0";
        }
        String expressStr = Stream.of(expressAry).collect(Collectors.joining(StringUtils.EMPTY));
        return expressStr;
    }

}
