package com.ythd.ower.common.tools;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {

    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_3 = "hh:mm:ss";
    public static final String DATE_FORMAT_4 = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_5 = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_FORMAT_6 = "MM月dd日";
    public static final String DATE_FORMAT_7 = "yyyy-MM";
    public static final String DATE_FORMAT_8 = "yyyyMMdd";
    public static final String DATE_FORMAT_9 = "M月d日  HH:mm";
    public static final String DATE_FORMAT_10 = "yyyy年M月d日 HH时mm分ss秒";
    public static final String DATE_FORMAT_12 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_13 = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String DATE_FORMAT_14 = "HH:mm:ss";
    public static final String DATE_FORMAT_15 = "HHmmss";
    public static final String HH_MM = "HH:mm";

    public TimeUtils() {
    }

    public static String getCurTime() {
        return dateToString(new Date(), "HH:mm:ss");
    }

    public static String getCurrentTime() {
        return dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getFormatCurrentTime(String format) {
        return dateToString(new Date(), format);
    }

    public static Date stringToDate(String dateString, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateString);
        } catch (ParseException var3) {
            throw new RuntimeException("Error when  getDateFromString from dateString, errmsg: " + var3.getMessage(), var3);
        }
    }

    public static Date toDateFormat_1(String dateString) {
        return stringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date toDateFormat_2(String dateString) {
        return stringToDate(dateString, "yyyy-MM-dd");
    }

    public static Date toDateFormat_4(String dateString) {
        return stringToDate(dateString, "yyyyMMddHHmmss");
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static long diff(Date endDate, Date startDate) {
        long endTime = getMillis(endDate);
        long startTime = getMillis(startDate);
        return endTime - startTime;
    }

    public static boolean diff(Date endDate, Date startDate, int n) {
        long endTime = getMillis(endDate);
        long startTime = getMillis(startDate);
        return endTime - startTime - (long)(n * 24 * 3600) * 1000L > 0L;
    }

    public static boolean diffY(Date endDate, Date startDate) {
        String endTime = toStringFormat_2(endDate);
        String startTime = toStringFormat_2(startDate);
        return endTime.compareTo(startTime) >= 1;
    }

    public static long getMillis(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal.getTimeInMillis();
    }

    public static String toStringFormat_1(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toStringFormat_2(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }

    public static String toStringFormat_4(Date date) {
        return dateToString(date, "yyyyMMddHHmmss");
    }

    public static String toStringFormat_5(Date date) {
        return dateToString(date, "yyyy年MM月dd日 HH:mm");
    }

    public static String toStringFormat_6(Date date) {
        return dateToString(date, "MM月dd日");
    }

    public static String toStringFormat_7(Date date) {
        return dateToString(date, "yyyy-MM");
    }

    public static String toStringFormat_8(Date date) {
        return dateToString(date, "yyyyMMdd");
    }

    public static String toStringFormat_9(Date date) {
        return dateToString(date, "M月d日  HH:mm");
    }

    public static String toStringFormat_10(Date date) {
        return dateToString(date, "yyyy年M月d日 HH时mm分ss秒");
    }

    public static String toStringFormat_12(Date date) {
        return date == null ? "" : dateToString(date, "yyyy-MM-dd HH:mm");
    }

    public static Date getDateDiff(int n) {
        Date d = new Date();
        Date returnDay = new Date(d.getTime() + (long)(n * 24 * 3600) * 1000L);
        return returnDay;
    }

    public static String getDiffToString(Date date, int n, String pattern) {
        Date returnDay = new Date(date.getTime() + (long)(n * 24 * 3600) * 1000L);
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(returnDay);
    }

    public static Timestamp getTimestampDiff(int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        Date dayDiff = new Date(d.getTime() + (long)(n * 24 * 3600) * 1000L);
        String time = df.format(dayDiff);
        return Timestamp.valueOf(time);
    }

    public static Timestamp stringToTimestamp() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        return Timestamp.valueOf(time);
    }

    public static String parseToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(pattern);
        String str = null;
        if (date == null) {
            return null;
        } else {
            str = simpleDateFormat.format(date);
            return str;
        }
    }

    public static int compareWithNow(String t) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(formatter.parse(t));
            c2.setTime(new Date());
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return c1.compareTo(c2);
    }

    public static Date afterDateTime(Date dt, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.add(6, amount);
        return cal.getTime();
    }

    public static Date afterDateTimeByHour(Date dt, int amount) {
        int days = amount / 24;
        int hours = amount % 24;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        if (days > 0) {
            cal.add(6, days);
        }

        if (hours > 0) {
            cal.add(11, hours);
        }

        return cal.getTime();
    }

    public static Date getMaxDate(String dateStr, String dateFormat) {
        Date dt = stringToDate(dateStr, dateFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(10, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        return cal.getTime();
    }

    public static Date getMinDate(String dateStr, String dateFormat) {
        Date dt = stringToDate(dateStr, dateFormat);
        return getMinDate(dt);
    }

    public static Date getMinDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        return cal.getTime();
    }

    public static boolean isActivitied(String startDate, String endDate) {
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            startDate = startDate.trim();
            endDate = endDate.trim();
            Date sd = null;
            Date ed = null;
            if (startDate.length() == 10 && endDate.length() == 10) {
                sd = toDateFormat_2(startDate);
                ed = toDateFormat_2(endDate);
            } else if (startDate.length() == 19 && endDate.length() == 19) {
                sd = toDateFormat_1(startDate);
                ed = toDateFormat_1(endDate);
            }

            if (sd != null && ed != null) {
                Calendar startCal = Calendar.getInstance();
                startCal.setTime(sd);
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(ed);
                Calendar curCal = Calendar.getInstance();
                if (curCal.after(startCal) && curCal.before(endCal)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean comepareTime(String time1, String time2) {
        String time1Str = time1.replace(":", "");
        String time2Str = time2.replace(":", "");
        return Integer.parseInt(time1Str) < Integer.parseInt(time2Str);
    }

    public static Date beforeDate(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static String getNextDay(Date txDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(txDate);
        calendar.add(5, 1);
        Date nextDay = calendar.getTime();
        String nextDayStr = dateToString(nextDay, "yyyyMMdd");
        return nextDayStr;
    }

    public static Date newDatePlusHours(Date dt, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(11, hours);
        return calendar.getTime();
    }

    public static Date datePlusSeconds(Date dt, int secondes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(13, secondes);
        return calendar.getTime();
    }
}
