package com.example.administrator.potato.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/2.
 */

public class TodayInHistoryBeen implements Serializable {

    /**
     * date : 18871101
     * day : 1
     * event : 　　闽督杨昌濬、台抚刘铭传会奏，请将台湾必建行剩清廷据刘铭传建议，将台湾正式建省，辖三府一州五厅十一县：新设首府为台湾府，辖台湾、彰化、云林、苗栗四县和埔里社厅；原台湾府改为台南府，辖安平（原台湾县）、嘉义（原诸罗县）、凤山、恒春四县和澎湖厅；台北府辖淡水、新竹、宜兰三县和基隆厅、南雅厅；添设台东直隶州，由原卑南厅升置，辖花莲港厅。在清朝统一政权管辖下，台湾的经济、文化都得到发展。

     * id : 569881b7590146d407333036
     * month : 11
     * title : 台湾建省
     */

    private String date;
    private String event;
    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
