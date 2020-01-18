package com.example.administrator.potato.bean;

import java.io.Serializable;

/**
 * 作者 Administrator
 * 时间 2018/11/13
 */

public class DocBean implements Serializable {
    private String id;
    private String path;
    private int size;
    private String name;
    private String modifyDate;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
