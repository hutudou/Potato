package com.example.administrator.potato.utils;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureSelectionConfig;

/**
 * 作者 Administrator
 * 时间 2019/1/4
 */

public class PictureSelectorUtil {
    private static PictureSelectionConfig config;

    public static PictureSelectionConfig getImageConfig() {
        config = new PictureSelectionConfig();
        config.camera = false;  //是否是照相机
        config.imageSpanCount = 3;  //每行显示图片数量
        config.minSelectNum = 1;    //最小选择图片数
        config.maxSelectNum = 9;    //最大选择图片数
        config.selectionMode = PictureConfig.MULTIPLE;//单选或者多选
        config.enablePreview = true;    //是否可预览
        config.isCamera = false;         //是否开启照相机
        return config;
    }
}
