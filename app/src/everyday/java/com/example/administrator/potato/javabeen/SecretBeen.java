package com.example.administrator.potato.javabeen;

import java.io.Serializable;


/**
 * 作者 Administrator
 * 时间 2019/3/4
 */

public class SecretBeen implements Serializable {
    private int spotGoodNumber;
    private String objectId;
    private int commentNumber;
    private String secretContent;
    private String comment;
    private String nickName;
    private int id;
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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
