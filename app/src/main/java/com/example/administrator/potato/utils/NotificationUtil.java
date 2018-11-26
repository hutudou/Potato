package com.example.administrator.potato.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import com.example.administrator.potato.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.VISIBILITY_SECRET;

/**
 * 通知管理工具
 */
public class NotificationUtil {
    private String chanelId;
    private NotificationManager manager;
    private Notification.Builder builder;
    private Context mContext;
    //大图标
    private int largeIcon;
    //小图标
    private int smallIcon;

    public NotificationUtil(Context mContext, String chanelId, String notificationName) {
        this.mContext = mContext;
        this.chanelId = chanelId;
        // 初始化manager和builder
        manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(mContext);
        // 设置状态栏显示图标
        largeIcon = R.drawable.icon_notification;
        smallIcon = R.drawable.icon_notification;
        //如果是安卓8.0及以上 则设置消息渠道
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            initChanel(chanelId, notificationName);
        }
    }

    /**
     * 设置chanel 安卓8.0以上需要设置渠道 可以更方便的根据渠道id进行对通知的管理
     * @param chanelId                                      渠道id
     * @param notificationName                              通知的名字
     */
    private void initChanel(String chanelId, String notificationName) {
        NotificationChannel mChannel;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(chanelId, notificationName, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.canBypassDnd();//是否绕过请勿打扰模式
            mChannel.enableLights(true);//闪光灯
            mChannel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
            mChannel.setLightColor(Color.RED);//闪关灯的灯光颜色
            mChannel.canShowBadge();//桌面launcher的消息角标
            mChannel.enableVibration(true);//是否允许震动
            mChannel.getAudioAttributes();//获取系统通知响铃声音的配置
            mChannel.getGroup();//获取通知取到组
            mChannel.setShowBadge(true);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            mChannel.setBypassDnd(true);//设置可绕过  请勿打扰模式
            mChannel.setVibrationPattern(new long[]{500, 500, 1000});//设置震动模式
            mChannel.shouldShowLights();//是否会有灯光
            manager.createNotificationChannel(mChannel);
        }
    }

    /**
     * 使用系统默认的布局 但是不同系统中风格略有差异
     * @param largeIcon              大图标
     * @param smallIcon              小图标
     * @param notificationTitle      通知标题
     * @param notificationContent    通知内容
     * @param notificationId         通知id
     */
    public void sendNormalNotification(int largeIcon, int smallIcon, String notificationTitle, String notificationContent, int notificationId) {
        //小于0则使用默认图标
        if (largeIcon <= 0) {
            largeIcon = this.largeIcon;
        }
        if (smallIcon <= 0) {
            smallIcon = this.smallIcon;
        }
        setBuilder(largeIcon, smallIcon, notificationTitle, notificationContent);

        manager.notify(notificationId, builder.build());
    }

    /**
     * 设置builder
     * @param largeIcon                 大图标
     * @param smallIcon                 小图标
     * @param notificationTitle         通知标题
     * @param notificationContent       通知内容
     */
    private void setBuilder(int largeIcon, int smallIcon, String notificationTitle, String notificationContent) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), largeIcon);
        builder.setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setSmallIcon(smallIcon)
                .setLargeIcon(bitmap)
                //设置通知时间
                .setWhen(System.currentTimeMillis())
                //首次进入时显示效果
                .setTicker(notificationTitle)
                //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                .setDefaults(Notification.DEFAULT_SOUND);
        // 安卓8.0以上builder还需要绑定chanelId  不加此行8.0以上不显示通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(chanelId);
        }
    }

    public void sendCustomNotification() {

    }

    public boolean clearAllNotification(){
        manager.cancelAll();
        return true;
    }
}
