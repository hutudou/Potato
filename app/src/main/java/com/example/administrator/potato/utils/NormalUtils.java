package com.example.administrator.potato.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者 Administrator
 * 时间 2019/3/14
 * 平时可能用到的一些小工具
 */

public class NormalUtils {
    /**
     * 校验手机号
     */
    public static boolean isMobile(String mobile) {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
