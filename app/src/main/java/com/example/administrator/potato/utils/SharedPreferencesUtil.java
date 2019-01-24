package com.example.administrator.potato.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.administrator.potato.application.MyApplication;

/**
 * 作者 Administrator
 * 时间 2018/12/28
 * <p>
 * <p>
 * SharedPreferences管理工具
 */

public class SharedPreferencesUtil {

    //App信息
    private static final String APP_INFO = "appInfo";

    /**
     * 存储六大包装类型
     *
     * @param key   参数的key
     * @param value 参数的值
     */
    public static void saveData(@NonNull String key, Object value) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {//根据value的类型存入对应的类型
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        //提交
        editor.apply();
    }

    /**
     * 存储六大包装类型 需要指定存储文件名
     *
     * @param key   参数的key
     * @param value 参数的值
     */
    public static void saveDataWithFileName(@NonNull String name, @NonNull String key, Object value) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {//根据value的类型存入对应的类型
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        //提交
        editor.apply();
    }

    /**
     * 根据key删除值
     *
     * @param key key
     * @return 返回删除成功或者失败
     */
    public static boolean removeValueByKey(@NonNull String key) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains(key)) {//删除前先判断key是否存在
            editor.remove(key);
            editor.apply();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 根据key删除值 并指定删除的key所在的文件名
     *
     * @param key
     * @param name
     * @return
     */
    public static boolean removeValueByKey(@NonNull String key, @NonNull String name) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains(key)) {//删除前先判断key是否存在
            editor.remove(key);
            editor.apply();
        } else {
            return false;
        }
        return true;
    }

    /**
     * 获取值
     *
     * @param key          值的key
     * @param defaultValue 默认的值
     * @return 值
     */
    public static Object getData(@NonNull String key, @NonNull Object defaultValue) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {//根据defaultValue判断取得的类型
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        }
        return null;
    }

    /**
     * 获取值 指定文件名
     *
     * @param key          key
     * @param defaultValue 默认的值
     * @param name         文件名
     * @return 值
     */
    public static Object getData(@NonNull String key, @NonNull Object defaultValue, @NonNull String name) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {//根据defaultValue判断取得的类型
            return sharedPreferences.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultValue);
        }
        return null;
    }
}
