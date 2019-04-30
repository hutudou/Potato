package com.example.administrator.potato.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 作者 Administrator
 * 时间 2018/11/30
 * log  管理类
 */

public class Apploger {
    /**
     * 指定目录没有该文件则直接创建 有该文件则往后追加 可用于纪录接口请求数据
     *
     * @param file   文件地址
     * @param conent 纪录的内容
     */
    public static void writeLog(String file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
