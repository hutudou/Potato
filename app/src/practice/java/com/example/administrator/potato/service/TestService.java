package com.example.administrator.potato.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.potato.AppConstant;
import com.example.administrator.potato.utils.NotificationUtil;


/**
 * 作者 Administrator
 * 时间 2018/11/12
 * 服务demo  此demo实现使用服务开辟线程来进行计数  前台服务
 */

public class TestService extends Service {
    private static int nn;
    //线程的标志位 通过isStart控制线程逻辑部分是否执行
    private static boolean isStart = true;
    private NotificationUtil notificationUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationUtil = new NotificationUtil(this, "Count", "计数服务");
        //开启前台服务
        startForeground(1, new Notification());
        Log.d(AppConstant.LOG_NAME, "TestService: onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isStart = true;
        Log.d(AppConstant.LOG_NAME, "TestService: onStartCommand()");
        count(0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        isStart = false;
        Log.d(AppConstant.LOG_NAME, "TestService: onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(AppConstant.LOG_NAME, "TestService: onBind()");
        return null;
    }

    //计数 一秒记一次
    private void count(final int n) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isStart) {
                    notificationUtil.sendNormalNotification(-1, -1, "计数服务正在运行中", "当前数字为" + n, 123);
                    nn = n + 1;
                    try {
                        //线程休眠一秒以到达计时效果
                        Thread.sleep(1000);
                        count(nn);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
