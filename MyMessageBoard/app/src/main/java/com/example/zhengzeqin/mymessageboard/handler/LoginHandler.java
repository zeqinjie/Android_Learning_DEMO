package com.example.zhengzeqin.mymessageboard.handler;

import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengzeqin on 2017/11/26.
 */

public class LoginHandler extends BaseHandler {

//    public LoginHandler() {
//
//    }

    /* 1 登录成功且是管理员  */
    public void loginActionHandler(String userAccount, String userPassword){
        //是否管理员登录
        boolean isLoginSuccess;
        //普通会员登录
        HashMap <String,Object>hashMap = isNormalLoginSuccess(userAccount, userPassword);
        String status = (String)hashMap.get("status");
//        String authority = (String)hashMap.get("authority");
        if (status.equals("1")){
            UserModel userModel = (UserModel)hashMap.get("userModel");
            getHandlerDelegate().successHandler(hashMap,"登录成功");
        }else {//登录失败
            getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
        }
    }

    //是否管理员
    private HashMap isAdmin(String account,String password) {
        HashMap hashMap = new HashMap();
        if (account.equals("admin") && password.equals("admin")){
            List<UserModel> userModels = DataSupport.where("userAccount = ?", "admin").find(UserModel.class);
            UserModel userModel = null;
            if (userModels != null && !userModels.isEmpty()){
                userModel = userModels.get(0);
            }else{
                UserModel userModel1 = new UserModel();
                userModel1.setUserPassword(account);
                userModel1.setUserAccount(account);
                userModel1.setAuthority(1);
                userModel1.setUserName("管理员");
                userModel = userModel1;
            }
            hashMap.put("userModel",userModel);
            hashMap.put("status","1");
        }else{
            hashMap.put("status","0");
        }
        return hashMap;
    }

    //普通会员登录是否成功
    private HashMap<String,Object> isNormalLoginSuccess(String userAccount, String userPassword){
        HashMap hashMap = new HashMap();
        HashMap adminMap = isAdmin(userAccount, userPassword);
        String adminstatus = (String)adminMap.get("status");
        if (adminstatus.equals("1")){
            hashMap.put("authority",1);//管理员
            hashMap.put("status","1");
            UserModel userModel = (UserModel)adminMap.get("userModel");
            hashMap.put("userModel",userModel);
            return hashMap;
        }
        //查询数据表是否有该对象
//        List<UserModel> mDestList = DataSupport.findAll(UserModel.class);
        List<UserModel> userModels = DataSupport.where("userAccount = ?", userAccount).find(UserModel.class);
        UserModel userModel = null;
        if (userModels != null && !userModels.isEmpty()){
            userModel = userModels.get(0);
        }
        if (userModel != null){
//            return userModel;
            if (userModel.getUserPassword().equals(userPassword)){//登录成功
                hashMap.put("userModel",userModel);
                hashMap.put("status","1");
                hashMap.put("authority",userModel.getAuthority());
            }else {//登录失败
                hashMap.put("error","密码错误");
                hashMap.put("status","0");
            }
//            ContentValues values = new ContentValues();
//            values.put("authority", -1);//普通会员
//            DataSupport.update(UserModel.class, values, userModel.getId());
        }else {
            hashMap.put("error","该账号不存在,请注册");
            hashMap.put("status","0");
        }
        return hashMap;
    }



}

