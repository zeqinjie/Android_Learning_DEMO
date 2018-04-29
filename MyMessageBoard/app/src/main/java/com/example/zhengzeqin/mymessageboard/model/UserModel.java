package com.example.zhengzeqin.mymessageboard.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by zhengzeqin on 2017/11/26.
 */

public class UserModel extends DataSupport implements Serializable {

    /**/
    private int id;

    //用户名
    private String userName;

    /*账户*/
    private String userAccount;

    /*密码*/
    private String userPassword;

    /*-1: 普通会员  1：管理者  0：黑名单*/
    private int authority;

    /*是否当前操作对象*/
    private boolean isCurrentUse;

    /*是否之前登录*/
    private boolean isHadLogin;


    /*setter getter*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public boolean isCurrentUse() {
        return isCurrentUse;
    }

    public void setCurrentUse(boolean currentUse) {
        isCurrentUse = currentUse;
    }

    public boolean isHadLogin() {
        return isHadLogin;
    }

    public void setHadLogin(boolean hadLogin) {
        isHadLogin = hadLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //    public UserModel(String userAccount, String userPassword, int authority, boolean isCurrentUse) {
//        this.userAccount = userAccount;
//        this.userPassword = userPassword;
//        this.authority = authority;
//        this.isCurrentUse = isCurrentUse;
//    }
}


