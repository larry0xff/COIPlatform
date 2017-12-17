package zhongd.coiplatform.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author xiezd
 * @Description 日期工具类
 * @Date Created in  0:49 星期日 2017/12/17/017
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 日期运算
     *
     * @param date  源
     * @param part  操作部份
     * @param value 改变值
     * @return 计算后的日期
     */
    public static Date add(Date date, int part, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(part, value);
        return calendar.getTime();
    }

    /**
     * 取两个日期的差值
     *
     * @param from 开始时间
     * @param to   结束时间
     * @param part Calendar.SECOND--相关多少秒,Calendar.MINUTE--相关多少分,Calendar.HOUR_OF_DAY-时,other-天
     * @return 差值
     */
    public static long diff(Date from, Date to, int part) {
        if (to == null || from == null)
            return 0;
        long d = to.getTime() - from.getTime();
        switch (part) {
            case Calendar.SECOND:
                return d / 1000;
            case Calendar.MINUTE:
                return d / 1000 / 60;
            case Calendar.HOUR_OF_DAY:
                return d / 1000 / 60 / 60;
            default:
                return d / 1000 / 60 / 60 / 24;
        }

    }

    /**
     * 日期格式化函数,格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss", null);
    }

    /**
     * 日期格式化函数,缺省时区
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        return format(date, format, null);
    }

    /**
     * 日期格式化函数
     *
     * @param date
     * @param format
     * @param timeZone 时区如东八区GMT+8
     * @return
     */
    public static String format(Date date, String format, String timeZone) {
        if (date == null)
            return "";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if (timeZone != null && !timeZone.trim().equals(""))
            formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        return formatter.format(date);
    }

    /**
     * 字符转换为日期。
     *
     * @param source
     * @return
     */
    public static Date stringToDate(String source) {
        return stringToDate(source, "yyyy-MM-dd HH:mm:ss", null);
    }

    /**
     * 字符转换为日期。
     *
     * @param source
     * @param pattern 日期格式串如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        return stringToDate(source, pattern, null);
    }

    /**
     * 字符串转换为指定时区时间
     *
     * @param value
     * @param pattern  如yyyy-MM-dd HH:mm:ss
     * @param timeZone 如东八区GMT +8
     * @return
     */
    public static Date stringToDate(String value, String pattern, String timeZone) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        if (value == null)
            return date;
        if (timeZone != null && !timeZone.trim().equals(""))
            format.setTimeZone(TimeZone.getTimeZone(timeZone));
        try {
            date = format.parse(value);
        } catch (java.text.ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取当前日期是一个星期的第几天
     * <br>星期天是0
     */
    public static int getDayOfWeek(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    /**
     * 获取当前日期是星期几
     *
     * @return
     */
    public static String getWeekOfDate(Date time) {
        if (time == null)
            return "";
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return weekDays[getDayOfWeek(time)];
    }

    /**
     * 两个日期相差的天数，不算时分秒
     *
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static int daysBetween(Date from, Date to) {
        long fromL = stringToDate(format(from, "yyyy-MM-dd"), "yyyy-MM-dd").getTime() / (1000 * 24 * 60 * 60);
        long toL = stringToDate(format(to, "yyyy-MM-dd"), "yyyy-MM-dd").getTime() / (1000 * 24 * 60 * 60);
        return (int) (toL - fromL);
    }

    /**
     * 两个日期相差的天数，不算时分秒
     *
     * @param from 开始日期
     * @param to   结束日期
     * @return
     */
    public static int daysBetween(String from, String to) {
        if(from == null || to == null)
            return 0;
        long fromL = stringToDate(from, "yyyy-MM-dd").getTime() / (1000 * 24 * 60 * 60);
        long toL = stringToDate(to, "yyyy-MM-dd").getTime() / (1000 * 24 * 60 * 60);
        return (int) (toL - fromL);
    }

    /**
     * 得到两日期相差几个月
     */
    public static int getMonthDiff(String startDate, String endDate) {
        Date startDate1 = stringToDate(startDate, "yyyy-MM-dd");
        Date endDate1 = stringToDate(endDate, "yyyy-MM-dd");
        return getMonthDiff(startDate1, endDate1);
    }

    /**
     * 得到两日期相差几个月
     */
    public static int getMonthDiff(Date startDate, Date endDate) {
        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate);
        int sYear = starCal.get(Calendar.YEAR);
        int sMonth = starCal.get(Calendar.MONTH);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int eYear = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);

        return ((eYear - sYear) * 12 + (eMonth - sMonth));
    }

    /**
     * 获取当月最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        return calendar.getTime();
    }
}
