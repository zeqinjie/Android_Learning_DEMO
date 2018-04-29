package com.example.zhengzeqin.mymessageboard.handler;

import android.content.ContentValues;

import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;
import com.example.zhengzeqin.mymessageboard.utils.UserInfoUtil;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengzeqin on 2017/11/29.
 */

public class RegisterHandler extends BaseHandler {


    public void registerActionHandler(String userName,String userAccount, String userPassword,String code){
        HashMap <String,Object>hashMap = new HashMap();
        if (Integer.valueOf(code) != UserInfoUtil.getInstance().randNumCode){
            hashMap.put("error","验证码错误");
            hashMap.put("status","0");
            getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
        }else {
            hashMap.put("status","1");
            hashMap.put("authority",-1);
            UserModel userModel = new UserModel();
            userModel.setAuthority(-1);
            userModel.setUserAccount(userAccount);
            userModel.setUserPassword(userPassword);
            userModel.setUserName(userName);
            boolean success = userModel.save();
            if (success){
                hashMap.put("userModel",userModel);
                getHandlerDelegate().successHandler(hashMap,"注册成功");
            }else {
                hashMap.put("status","0");
                hashMap.put("error","数据库输入错误");
                getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
            }

        }
    }


    private void saveUserInfoModel(String userAccount,String userPassword,int authority){
        List<UserModel> userModels = DataSupport.where("userAccount = ?", userAccount).find(UserModel.class);
        UserModel userModel = userModels.get(0);
        if (userModel != null){
            ContentValues values = new ContentValues();
            values.put("isCurrentUse", true);
            DataSupport.update(UserModel.class, values, userModel.getId());
        }else {
            UserModel newUserModel = new UserModel();
            newUserModel.setUserAccount(userAccount);
            newUserModel.setUserPassword(userPassword);
            newUserModel.setCurrentUse(true);
            newUserModel.setAuthority(authority);
            newUserModel.save();
        }


    }
}
