package com.example.administrator.potato.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;


import java.io.File;

/**
 * @author potato
 * @date 2018/11/13
 */
public class FileUtils {
    /**
     * android获取一个用于打开HTML文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getHtmlFileIntent(String path, Context mContext) {
        File file = new File(path);
        Uri uri = Uri.parse(file.toString()).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(file.toString()).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(fitAndroid24(mContext, file), "text/html");
        return intent;
    }

    /**
     * android获取一个用于打开图片文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getImageFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "image/*");
        return intent;
    }

    /**
     * android获取一个用于打开PDF文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getPdfFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/pdf");
        return intent;
    }

    /**
     * android获取一个用于打开文本文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getTextFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "text/plain");
        return intent;
    }

    /**
     * android获取一个用于打开音频文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getAudioFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "audio/*");
        return intent;
    }

    /**
     * android获取一个用于打开视频文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getVideoFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "video/*");
        return intent;
    }


    /**
     * android获取一个用于打开CHM文件的intent
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getChmFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/x-chm");
        return intent;
    }


    /**
     * android获取一个用于打开Word文件的intent
     *
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getWordFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //读取权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/msword");
        return intent;
    }

    /**
     * android获取一个用于打开Excel文件的intent
     *
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getExcelFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/vnd.ms-excel");
        return intent;
    }

    /**
     * android获取一个用于打开PPT文件的intent
     *
     * @param path
     * @param mContext
     * @return
     */
    public static Intent getPPTFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/vnd.ms-powerpoint");
        return intent;
    }

    //android获取一个用于打开apk文件的intent
    public static Intent getApkFileIntent(String path, Context mContext) {
        File file = new File(path);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(fitAndroid24(mContext, file), "application/vnd.android.package-archive");
        return intent;
    }

    //适配sdk24以上
    private static Uri fitAndroid24(Context mContext, File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //即是在清单文件中配置的authorities
            uri = FileProvider.getUriForFile(mContext, "com.example.administrator.potato.mAuthorities", file);
            return uri;
            // 给目标应用一个临时授权
        } else {
            uri = Uri.fromFile(file);
            return uri;
        }
    }
}
