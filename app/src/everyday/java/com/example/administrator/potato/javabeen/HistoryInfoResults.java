package com.example.administrator.potato.javabeen;

import java.io.Serializable;

/**
 * 作者 Administrator
 * 时间 2018/12/14
 */

public class HistoryInfoResults implements Serializable {

    /**
     * date : 20051111
     * day : 11
     * event : 　　北京时间11月11日晚，万众期盼的北京2008年奥运吉祥物精彩出炉，五个可爱的福娃从此被赋予了生命，和中国人民一起，大声向世界说出“北京欢迎你”。
     　　在开场喜庆又不失京派风韵的鼓乐演奏之后，白岩松，王小丫，朱军和春妮四位主持人联袂登场，北京08奥运吉祥物悬念的揭晓大幕就此揭开。
     　　首先，由藏族、蒙古族、朝鲜族、维吾尔族等少数民族歌手奉上一曲民族欢歌的大联唱，他们用热情的声音和动人的舞姿展示出中国多民族多文化融合的团结氛围。随后著名歌星孙楠和香港歌手谢霆锋分别演唱了《爱在北京》和《为你荣光》两首歌曲，前者深情，后者激奋，旋律有别但始终围绕着爱的主题。
     　　接下来，实力唱将韦唯用《共迎奥运》唱出了中国人民对于奥运会的期待，现场大屏幕开始对历届奥运吉祥物进行回顾。此时镜头忽然转换，天安门广场上倒计时牌上“距离2008年8月8日奥运会开幕还有1000天”已经赫然在目。北京奥运的口号是“one world，one dream”，那英、刘欢携手共同高歌了与这一口号同名歌曲《同一世界，同一梦想》。
     　　奥运吉祥物发布仪式正式开始，来自各国和社会各界的朋友以及天真的孩子们纷纷走上台来，跨栏王子刘翔笑意盈盈，跳水皇后郭晶晶更是光彩照人，奥运冠军们的到来预示着最后高潮的来临。
     　　全国政协主席贾庆林为揭开了谜底，五块白板上呈现了5个颜色不同、形态各异、活泼可爱的福娃娃。第一个红色的是福娃“欢欢”，以奥运圣火为原型；第二个黑白相间的是福娃“晶晶”，原型为国宝大熊猫；此外还有原型为鱼儿，象征江河湖海的福娃“贝贝”，原型为藏羚羊的福娃“莹莹”和原型为燕子的福娃“妮妮”。这五个福娃从色彩上正好呼应了奥运五环的红橙蓝绿黑，而他们的名字谐音连起来恰好是“北京欢迎你”。
     　　歌声再度响起来，靓丽的青春组合和人气正旺的“超女”选手李宇春、张靓颖等共同唱起了“北京欢迎你……”华人巨星成龙也出现在人群中，挥着胳膊，和着“四海一家亲兄弟”的欢快节奏跳着唱着。扮成各个福娃模样的孩子们，现场巨大的塑料充气福娃，都舞动着，欢庆着，从此，这组新形象将和中国人民一起，迎接2008年北京奥运会的到来。

     * id : 569881b7590146d407333107
     * month : 11
     * title : 北京奥运吉祥物揭晓
     */

    private String date;
    private int day;
    private String event;
    private String id;
    private int month;
    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
