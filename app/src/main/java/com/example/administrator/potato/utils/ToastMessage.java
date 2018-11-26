package com.example.administrator.potato.utils;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.administrator.potato.application.MyApplication;

import es.dmoral.toasty.Toasty;

/**
 * Created by potato on 2018/7/30.
 */

public class ToastMessage {

    /**
     *弹出成功消息
     *
     *@param text 需要显示的消息
     *@param isShowIcon 是否需要显示图标 默认显示
     * */
    public static void toastSuccess(@NonNull String text, Boolean isShowIcon){
        if(isShowIcon==null){
            isShowIcon=true;
        }
        Toasty.success(MyApplication.getContext(),text, Toast.LENGTH_SHORT,isShowIcon).show();
    }

    /**
     *弹出错误消息
     *
     *@param text 需要显示的消息
     *@param isShowIcon 是否需要显示图标 默认显示
     * */
    public static void toastError(@NonNull String text, Boolean isShowIcon){
        if(isShowIcon==null){
            isShowIcon=true;
        }
        Toasty.error(MyApplication.getContext(),text, Toast.LENGTH_SHORT,isShowIcon).show();
    }

    /**
     *弹出一般消息
     *
     *@param text 需要显示的消息
     * */
    public static void toastNormal(@NonNull String text){
        Toasty.normal(MyApplication.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    /**
     *弹出警告消息
     *
     *@param text 需要显示的消息
     *@param isShowIcon 是否需要显示图标 默认显示
     * */
    public static void toastWarn(@NonNull String text, Boolean isShowIcon){
        if(isShowIcon==null){
            isShowIcon=true;
        }
        Toasty.warning(MyApplication.getContext(),text, Toast.LENGTH_SHORT,isShowIcon).show();
    }
}
