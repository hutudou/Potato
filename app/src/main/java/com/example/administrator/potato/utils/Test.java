package com.example.administrator.potato.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者 Administrator
 * 时间 2018/12/21
 */

public class Test {
    public static void main(String args[]) {
        System.out.println("时间转换为时间戳:");
        System.out.println(DateUtils.dateToTimeStamp(DateUtils.getLocalDate("yyyy-MM-dd hh-mm-ss"), "yyyy-MM-dd hh-mm-ss"));

        System.out.println("\n时间戳转换为时间:");
        System.out.println(DateUtils.timeStampToDate("yyyy-MM-dd hh-mm-ss", DateUtils.dateToTimeStamp(DateUtils.getLocalDate("yyyy-MM-dd hh-mm-ss"), "yyyy-MM-dd hh-mm-ss")));

        System.out.println("\n秒转换为时间:");
        Map<String, Long> map = new HashMap<>();
        map = DateUtils.secondToTime(18339);
        System.out.println(DateUtils.secondToTime(18339));

        final long day = map.get("day");
        final long hour = map.get("hour");
        final long minute = map.get("minute");
        final long second = map.get("second");
    }
}
