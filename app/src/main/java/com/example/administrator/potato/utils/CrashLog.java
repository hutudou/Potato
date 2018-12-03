package com.example.administrator.potato.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.administrator.potato.AppConstant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * 存储设备信息以及异常信息
 */
public class CrashLog {

    public static void saveCrashLog(Context context, Throwable throwable) {
        Map<String, String> map = collectDeviceInfo(context);
        saveCrashInfo2File(throwable, map);
    }


    //收集设备信息
    private static Map<String, String> collectDeviceInfo(Context ctx) {
        Map<String, String> infos = new TreeMap<>();
        try {
            infos.put("systemVersion", Build.VERSION.RELEASE);
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
            }
        }
        return infos;
    }

    private static void saveCrashInfo2File(Throwable ex, Map<String, String> infos) {
        StringBuilder sb = new StringBuilder();
        //循环输出设备信息
        sb.append("   ****************************************************\n");
        sb.append(" *                                                      *\n");
        sb.append("*                     设备信息                            *\n");
        sb.append(" *                                                      *\n");
        sb.append("   ****************************************************\n");
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append("=").append(value).append("\n");
        }
        //收集异常信息
        sb.append("   ****************************************************\n");
        sb.append(" *                                                      *\n");
        sb.append("*                     错误信息                            *\n");
        sb.append(" *                                                      *\n");
        sb.append("   ****************************************************\n");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        //将log保存至指定文件夹
        saveLog(sb.toString());
    }

    //写入文件
    private static void saveLog(String fileContent) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        String time = DateUtils.getLocalDate("YYYY-MM-dd HH:mm:ss");
        String fileName = "CrashLog-" + time + ".doc";
        try {
            //判断当前SD卡状态 为正常时才可以写入文件
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(AppConstant.CRASH_LOG);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(AppConstant.CRASH_LOG, fileName);
                if (file.exists()) {
                    file.delete();
                }
                try {
                    fos = new FileOutputStream(file);
                    bos = new BufferedOutputStream(fos);
                    bos.write(fileContent.getBytes());
                    Log.d("awei", "文件保存成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.d("awei", "文件保存失败");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.d("awei", e.getLocalizedMessage());
        } finally {
            //关闭流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
