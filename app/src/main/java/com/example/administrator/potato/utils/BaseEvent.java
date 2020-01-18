package com.example.administrator.potato.utils;

/**
 * EventBus基础事件
 *
 * @author potato
 */
public class BaseEvent {
    public int eventType;
    public Object data;

    public BaseEvent(int eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
    }
}
