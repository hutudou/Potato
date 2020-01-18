package com.example.administrator.potato.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import com.example.administrator.potato.R;
import com.example.administrator.potato.application.MyApplication;

import java.util.concurrent.ExecutionException;

/**
 * 自定义属性管理工具
 *
 * @author Administrator
 * @date 2019/1/23
 */

public class AppCustomerAttrsUtil {

    /**
     * 得到color
     *
     * @param attrs    自定义属性总名字
     * @param attrName 自定义属性名
     * @return 返回的值
     */
    public static int getColor(Context context, int attrs[], int attrName) {
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs);
            int color = typedArray.getColor(attrName, Color.RED);
            typedArray.recycle();
            return color;
        } catch (Exception e) {
            return -1;
        }
    }
}
