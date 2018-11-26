package com.example.administrator.potato.utils;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者:土豆
 * 创建日期:2018/8/9
 * 邮箱:1401552353@qq.com
 */

public class DateUtils {
    private static Calendar mCalendar = Calendar.getInstance();

    /**
     * 得到某年某月的最后一天  当传入的月份大于12时  会自动换算成正确的年月日
     *
     * @param year  年
     * @param month 月
     * @return String
     */
    public static String getEndDayOfMonth(int year, int month) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mCalendar.set(Calendar.HOUR_OF_DAY, 23);
        mCalendar.set(Calendar.MINUTE, 59);
        mCalendar.set(Calendar.SECOND, 59);
        //月份总天数减一即是上月份的最后一天
        mCalendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = mCalendar.getTime();
        //lastDate示例: Tue Feb 29 23:59:59 GMT+08:00 2000  使用substring来截取天数所对应的位置
        return lastDate.toString().substring(8, 10);
    }

    /**
     * 把日期转为指定格式的字符串
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return String
     */

    public static String ConvertDateToString(@NonNull Date date, @NonNull String dateType) {
        SimpleDateFormat df = new SimpleDateFormat(dateType);
        return df.format(date);
    }

    /**
     * 按照指定格式得到前几天或者后几天的
     *
     * @param appointDay 负数表示以前 正数表示未来
     * @param dateType   日期格式
     * @return String
     */
    public static String getAppointDate(int appointDay, @NonNull String dateType) {
        mCalendar.add(Calendar.DATE, appointDay);
        Date date = mCalendar.getTime();
        return ConvertDateToString(date, dateType);
    }

    /**
     * 按照指定格式得到当前时间
     *
     * @param type 时间格式
     *             return String
     */
    public static String getLocalDate(String type) {
        return DateUtils.ConvertDateToString(mCalendar.getTime(), type);
    }

    /**
     * 转换时间戳
     *
     * @param dataFormat 需要转换的日期格式
     * @param timeStamp  时间戳
     * @return
     */
    public static String formatTimeStamp(String dataFormat, long timeStamp) {
        timeStamp = timeStamp * 1000;
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        String result = format.format(new Date(timeStamp));
        return result;
    }


}
