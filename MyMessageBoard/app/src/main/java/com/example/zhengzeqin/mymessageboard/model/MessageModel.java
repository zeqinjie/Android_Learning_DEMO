package com.example.zhengzeqin.mymessageboard.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by zhengzeqin on 2017/11/26.
 */

public class MessageModel extends DataSupport {
    private int id;

    private String title;

    private String userName;

    private String context;

    private Date publishDate;

    private int userId;


    //getter setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
