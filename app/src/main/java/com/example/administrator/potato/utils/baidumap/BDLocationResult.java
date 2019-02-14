package com.example.administrator.potato.utils.baidumap;

/**
 * 作者:土豆
 * 创建日期:2018/9/6
 * 邮箱:1401552353@qq.com
 */

public class BDLocationResult {
    static Double latitude;//纬度
    static Double longitude;//经度
    static String address;//详细地址
    static String city;//当前定位城市
    static String province;//当前定位省份
    static String district;//当前定位区
    static boolean locationFlag = false;//判断程序自启动后 是否定位成功过
}
