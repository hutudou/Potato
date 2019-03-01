package com.example.administrator.potato.javabeen;

import java.io.Serializable;

/**
 * 作者 Administrator
 * 时间 2019/2/28
 */

public class WeatherBeen implements Serializable {

    /**
     * date : 2019-03-04
     * dayTime : 阴
     * night : 小雨
     * temperature : 14°C / 10°C
     * week : 星期一
     * wind : 微风 小于3级
     */

    private String date;
    private String dayTime;
    private String night;
    private String temperature;
    private String week;
    private String wind;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
