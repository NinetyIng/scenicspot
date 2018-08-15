package com.ythd.ower.common.tools;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class EADate {

	/**
	 * 年月日数字如：20101201
	 */
	public static String FORMAT_DATE_STR = "yyyyMMdd";
	/**
	 * 年月日数字如：201012
	 */
	public static String FORMAT_YYYYMM_STR = "yyyyMMdd";
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_ORDER_SN = "yyMMddHHmmss";
	/**
	 * 英文全称 如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_MMDD = "MM-dd";
	
	
	/**
	 * 中文简写 如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	/**
	 * 中文全称 如：2010年12月01日 23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
	
	

	
	
	

	/**
	 * 获取指定年份和月份对应的天数
	 * 
	 * @param year
	 *            指定的年份
	 * @param month
	 *            指定的月份
	 * @return int 返回天数
	 */
	public static int getDaysInMonth(int year, int month) {
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
				|| (month == 12)) {
			return 31;
		} else if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
			return 30;
		} else {
			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String date2StrSimple(Date date) {
		return date2Str(date, "yyyy-MM-dd");
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 根据所给的起止时间来计算间隔的天数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔天数
	 */
	public static int getIntervalDays(java.sql.Date startDate, java.sql.Date endDate) {
		long startdate = startDate.getTime();
		long enddate = endDate.getTime();
		long interval = enddate - startdate;
		int intervalday = (int) (interval / (1000 * 60 * 60 * 24));
		return intervalday;
	}

	/**
	 * 根据所给的起止时间来计算间隔的月数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔月数
	 */
	@SuppressWarnings("static-access")
	public static int getIntervalMonths(java.sql.Date startDate, java.sql.Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		int startDateM = startCal.MONTH;
		int startDateY = startCal.YEAR;
		int enddatem = endCal.MONTH;
		int enddatey = endCal.YEAR;
		int interval = (enddatey * 12 + enddatem) - (startDateY * 12 + startDateM);
		return interval;
	}

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
    /**
     * 根据传过来的时候格式化
     */
	public static String getFormatTime(Date date,String format){
		if(EAString.isNullStr(format)){
			format = FORMAT_FULL;
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}
	
	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyymmddhhmmss
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static BigDecimal getCurrentTimeAsNumber() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		returnStr = f.format(date);
		return new BigDecimal(returnStr);
	}

	/**
	 * 返回自定义格式的当前日期时间字符串
	 * 
	 * @param format
	 *            格式规则
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	/**
	 * 返回当前字符串型日期
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = simpledateformat.format(calendar.getTime());
		return strDate;
	}

	/**
	 * 返回指定格式的字符型日期
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String Date2String(Date date, String formatString) {
		if (EAUtil.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat simpledateformat = new SimpleDateFormat(formatString);
		String strDate = simpledateformat.format(date);
		return strDate;
	}

	/**
	 * 返回当前字符串型日期
	 * 
	 * @param format
	 *            格式规则
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate(String format) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		String strDate = simpledateformat.format(calendar.getTime());
		return strDate;
	}

	/**
	 * 返回数组存放年月日
	 */
	public static int[] getYearAndMonthAndDay(Calendar calendar) {

		int[] ymdh = new int[5];
		if (calendar == null) {
			calendar = Calendar.getInstance();// 使用日历类
		}
		ymdh[0] = calendar.get(Calendar.YEAR);
		ymdh[1] = calendar.get(Calendar.MONTH) + 1;
		ymdh[2] = calendar.get(Calendar.DAY_OF_MONTH);// 得到天
		ymdh[3] = calendar.get(Calendar.HOUR);// 得到小时
		return ymdh;

	}

	/**
	 * 根据日期获取星期
	 * 
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = sdfInput.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

	/**
	 * 将字符串型日期转换为日期型
	 * 
	 * @param strDate
	 *            字符串型日期
	 * @param srcDateFormat
	 *            源日期格式
	 * @param dstDateFormat
	 *            目标日期格式
	 * @return Date 返回的util.Date型日期
	 */
	public static Date stringToDate(String strDate, String srcDateFormat, String dstDateFormat) {
		Date rtDate = null;
		Date tmpDate = (new SimpleDateFormat(srcDateFormat)).parse(strDate, new ParsePosition(0));
		String tmpString = null;
		if (tmpDate != null)
			tmpString = (new SimpleDateFormat(dstDateFormat)).format(tmpDate);
		if (tmpString != null)
			rtDate = (new SimpleDateFormat(dstDateFormat)).parse(tmpString, new ParsePosition(0));
		return rtDate;
	}

	/**
	 * 字符串转换成日期,使用默认的日期格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return date
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转换成日期,使用默认的日期格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return date
	 */
	public static Date stringToDateSimple(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static String getMMDD(String asString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse(asString);
			format = new SimpleDateFormat("MM月dd日");
			return format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	public static Date stringToDate(String str, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将一个字符串的日期格式转成另一个日期格式
	 * @param str
	 * @param formatStr
	 * @param targetFormat
	 * @return
	 */
	public static String stringToStr(String str, String formatStr,String targetFormatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		SimpleDateFormat targetFormat = new SimpleDateFormat(targetFormatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return targetFormat.format(date);
	}
	
	
	/**
	 * 将年月日以字符串相加返回数字
	 * 
	 * @return
	 */
	public static String getDateStr(String format) {
		SimpleDateFormat df = null;
		if (format == null) {
			df = new SimpleDateFormat(FORMAT_DATE_STR);
		} else {
			df = new SimpleDateFormat(format);
		}
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取时间戳
	 */
	public static String getTimeString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取时间戳
	 */
	public static String getOrderSnString() {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_ORDER_SN);
		Calendar calendar = Calendar.getInstance();
		return df.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getYear(Date date) {
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
		return df.format(date).substring(0, 4);
	}

	/**
	 * 功能描述：返回月
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}
	
	/**
	 * 功能描述：返回毫
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 当前星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		return dateFm.format(date);
	}

	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 返回指定日期的字符串值;格式为YYYY MM DD HH MM SS
	 * 
	 * @param date
	 *            日期
	 * @param type
	 *            1返回年 2 返回年月 3 返回年月日,4年月日时,5年月日时分 6年月日时分秒
	 * @return
	 */
	public static String getDateAsStr(Date date, int type) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		returnStr = f.format(date);
		if (type == 1) {
			return returnStr.substring(0, 4);
		}
		if (type == 2) {
			return returnStr.substring(0, 6);
		}
		if (type == 3) {
			return returnStr.substring(0, 8);
		}
		if (type == 4) {
			return returnStr.substring(0, 10);
		}
		if (type == 5) {
			return returnStr.substring(0, 12);
		}
		if (type == 6) {
			return returnStr;
		}
		return "";
	}
	private static Calendar calendar;
	/**
	 * 得到几天前的时间(String)
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return date2Str(now.getTime());
	}
	/**
	 * 得到几年后的时间(String)
	 * @param d
	 * @return
	 */
	public static String getDateBeforeYear(Date d, int year) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.YEAR, now.get(Calendar.YEAR)+year);
		return date2Str(now.getTime());
	}
	/**
	 * 得到几天后的时间(String)
	 * @param d
	 * @param day
	 * @return
	 */
	public static String getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return date2Str(now.getTime());
	}
	/**
	 * 得到几个小小时后 几个小时 后   几个月后的时间 
	 * 0 秒  1分钟  2小时  3天  4月  5年
	 */
	public static Date getAfterDate(Date d,int type,int hour){
		Calendar now = Calendar.getInstance();
		if(d != null){
			now.setTime(d);
		}else{
			now.setTime(new Date());
		}
		if(1 == type){
			now.set(Calendar.MINUTE, now.get(Calendar.MINUTE)+hour);
		}else if(2 == type){
			now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY)+hour);
		}else if(3 == type){
			now.set(Calendar.DATE, now.get(Calendar.DATE)+hour);
		}else if(4 == type){
			now.set(Calendar.MONTH, now.get(Calendar.MONTH)+hour);
		}else if(5 == type){
			now.set(Calendar.YEAR, now.get(Calendar.YEAR)+hour);
		}else if(0 == type){
			now.set(Calendar.SECOND, now.get(Calendar.SECOND)+hour);
		}
		return now.getTime();
	}
	/**
	 * 比较两个时间的大小
	 */
	public static boolean compareTime(Date target,Date compare){
		return target.getTime() > compare.getTime(); 
	}
	
	/**
	 * 将数字月份转换成 可理解的字符串月份
	 * 
	 */
	public static String monthToStr(int month){
		
		String monthStr = "";
		switch (month) {
		case 1:
			monthStr = "一月";
			break;
		case 2:
			monthStr = "二月";
			break;
		case 3:
			monthStr = "三月";
			break;
		case 4:
			monthStr = "四月";
			break;
		case 5:
			monthStr = "五月";
			break;
		case 6:
			monthStr = "六月";
			break;
		case 7:
			monthStr = "七月";
			break;
		case 8:
			monthStr = "八月";
			break;
		case 9:
			monthStr = "九月";
			break;
		case 10:
			monthStr = "十月";
			break;	
		case 11:
			monthStr = "十一月";
			break;
		case 12:
			monthStr = "十二月";
			break;	
		default:
			break;
		}
		return monthStr;
	}
	
	
	
	public static String secToTime(int time) {  
        String timeStr = null;  
        int hour = 0;  
        int minute = 0;  
        int second = 0;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    } 
	public static String unitFormat(int i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Integer.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
	
	/**
	 * 获取给定日期当前月份中的最后一天日期
	 */
	public static Date getLastDate(Date currentDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	/**
	 * 获取给定日期当前月份中第一天
	 */
	public static Date getFirstDate(Date currentDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
	
	
	public static Date getContentYearMonthDay(String year,String month,String day){
		String dateStr = year+"-"+month+"-"+day;
		return stringToDate(dateStr,"yyyy-MM-dd");
	}
	
	
	   public static int differentDaysByMillisecond(Date date1,Date date2)
	    {
		   
		   int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		   if((date2.getTime() - date1.getTime())%(1000*3600*24) != 0){
			   days++;
		   }
	        return days;
	    }
	   
	
		public static void main(String[] args) {
			System.out.println(getWeekOfDate(new Date()));
		}

	
		 public static int getWeekOfDate(Date dt) {
		        //String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(dt);
		        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		        if (w < 0)
		            w = 0;
		        return w;
		    }
}
