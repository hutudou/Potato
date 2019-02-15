package com.example.administrator.potato.utils.baidumap;

/**
 * 作者:土豆
 * 创建日期:2018/9/6
 * 邮箱:1401552353@qq.com
 */

public class BDLocationResult {
    public static Double latitude;//纬度
    public static Double longitude;//经度
    public static String address;//详细地址
    public static String city;//当前定位城市
    public static String province;//当前定位省份
    public static String district;//当前定位区
    public static boolean locationFlag = false;//判断程序自启动后 是否定位成功过
}
