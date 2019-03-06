package com.example.administrator.potato.bmobbeen;

import cn.bmob.v3.BmobObject;

/**
 * 作者 Administrator
 * 时间 2019/3/1
 */

public class Person extends BmobObject {
    private String userName;
    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
