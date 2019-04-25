package com.example.administrator.potato.bmobbeen;

import cn.bmob.v3.BmobObject;

/**
 * 作者 Administrator
 * 时间 2019/3/4
 */

public class Secret extends BmobObject {
    private int spotGoodNumber;
    private int commentNumber;
    private String secretContent;
    private String comment;
    private String nickName;
    private int id;
    private String account;

    public int getSpotGoodNumber() {
        return spotGoodNumber;
    }

    public void setSpotGoodNumber(int spotGoodNumber) {
        this.spotGoodNumber = spotGoodNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getSecretContent() {
        return secretContent;
    }

    public void setSecretContent(String secretContent) {
        this.secretContent = secretContent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
