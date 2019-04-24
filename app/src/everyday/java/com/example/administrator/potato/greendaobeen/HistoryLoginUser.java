package com.example.administrator.potato.greendaobeen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.sql.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者 Administrator
 * 时间 2019/4/16
 */
@Entity
public class HistoryLoginUser {
    private String id;
    @Id
    private String account;//账号
    private String introduce;//个性签名
    private String nickName;//昵称
    private String password;//密码
    private long loginDate;
    @Generated(hash = 887082091)
    public HistoryLoginUser(String id, String account, String introduce,
            String nickName, String password, long loginDate) {
        this.id = id;
        this.account = account;
        this.introduce = introduce;
        this.nickName = nickName;
        this.password = password;
        this.loginDate = loginDate;
    }
    @Generated(hash = 1998094665)
    public HistoryLoginUser() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getIntroduce() {
        return this.introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getLoginDate() {
        return this.loginDate;
    }
    public void setLoginDate(long loginDate) {
        this.loginDate = loginDate;
    }
}
