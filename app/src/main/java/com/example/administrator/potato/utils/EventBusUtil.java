package com.example.administrator.potato.utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBusUtil工具类
 */
public class EventBusUtil {
    private static volatile EventBusUtil instance;
    //双重检验锁实现单例
    public static EventBusUtil getInstance() {
        if (instance == null) {
            synchronized (EventBusUtil.class) {
                if (instance == null) {
                    instance = new EventBusUtil();
                    return instance;
                }
            }
        }
        return instance;
    }

    /**
     * 注册EventBus
     *
     * @param object
     */
    public void registerEventBus(Object object) {
        //先监测是否没有注册
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    /**
     * 注销EventBus
     *
     * @param object
     */
    public void unregisterEventBus(Object object) {
        //先监测是否已经注册
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
            EventBus.getDefault().removeStickyEvent(object);
        }
    }

    /**
     * 发送基础事件
     *
     * @param event
     */
    public void postNormal(BaseEvent event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     *
     * @param event
     */
    public void postSticky(BaseEvent event) {
        EventBus.getDefault().postSticky(event);
    }

    public interface IEventBus {

        void register();

        void unregister();

        /**
         * @Subscribe(threadMode = ThreadMode.MAIN)在ui线程执行 操作ui必须在此线程
         * @Subscribe(threadMode = ThreadMode.BACKGROUND)在后台线程执行
         * @Subscribe(threadMode = ThreadMode.ASYNC)强制在后台执行
         * @Subscribe(threadMode = ThreadMode.POSTING)默认方式,
         */
        @Subscribe(threadMode = ThreadMode.POSTING)
        void onEvent(BaseEvent event);
    }
}
