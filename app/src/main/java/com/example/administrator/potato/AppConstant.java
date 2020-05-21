package com.example.administrator.potato;

import android.os.Environment;

import java.io.File;

/**
 * app中的常量
 */

public class AppConstant {
    /**
     * mob申请的app key
     */
    public static final String MOB_APP_KEY = "2568b8e7ec06f";
    /**
     * 全局log名字
     */
    public static final String LOG_NAME = "awei";
    /**
     * 功能二十一演示时的文件临时存放的路径
     */
    public static final String TEMP_FILE = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "1Potato_App"
            + File.separator
            + "Every";
    /**
     * 程序异常日志保存路径
     */
    public static final String CRASH_LOG = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + "1Potato_App"
            + File.separator
            + "crash_log";
    /**
     * 当前app主题
     */
    public static final String CURRENT_APP_THEME = "currentAppTheme";
    /**
     * 更换颜色后是否需要展示欢迎
     */
    public static final String IS_SHOW_COLOR_WELCOME = "isShowColorWelcome";
    /**
     * Bmob Application Id
     */
    public static final String BMOB_APPLICATION_ID = "28caea759127ddd035530fc5e87d104e";
    /**
     * 账号
     */
    public static final String ACCOUNT = "account";
    /**
     * 密码
     */
    public static final String PASSWORD = "password";
    /**
     * 昵称
     */
    public static final String NICKNAME = "nickName";
    /**
     * 个性签名
     */
    public static final String INTRODUCE = "introduce";

}
