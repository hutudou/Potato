package com.example.administrator.potato.bmobbeen;

import cn.bmob.v3.BmobObject;

/**
 * 作者 Administrator
 * 时间 2019/3/20
 */

public class Person extends BmobObject {

    //个性签名
    private String introduce;

    //昵称
    private String nickName;

    //密码(Md5)加密
    private String password;

    //账号
    private String account;


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
