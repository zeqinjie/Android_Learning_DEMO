package com.example.zhengzeqin.mymessageboard.handler;

import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.model.MessageModel;
import com.example.zhengzeqin.mymessageboard.model.UserModel;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by zhengzeqin on 2017/11/27.
 */

public class MessageBoradHandler extends BaseHandler {

    public void sendMessage(String title, String context, UserModel userModel){
        HashMap<String,Object> hashMap = new HashMap();
        if (userModel != null){
            if (userModel.getAuthority() == 0){
                hashMap.put("status","0");
                hashMap.put("error","黑名单用户不能留言");
                getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
                return;
            }
            hashMap.put("status","1");
            MessageModel messageModel = new MessageModel();
            String userName = userModel.getUserName();
            if (userName != null){
                messageModel.setUserName(userName);
            }else {
                messageModel.setUserName("匿名");
            }
            messageModel.setUserId(userModel.getId());
            messageModel.setTitle(title);
            messageModel.setContext(context);
            messageModel.setPublishDate(new Date());
            boolean success = messageModel.save();
            if (success){
                hashMap.put("userModel",userModel);
                getHandlerDelegate().successHandler(hashMap,"添加留言成功");
            }else {
                hashMap.put("status","0");
                hashMap.put("error","留言插入数据库错误");
                getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
            }
        }else {
            hashMap.put("status","0");
            hashMap.put("error","用户没有登录");
            getHandlerDelegate().failHandler(hashMap,(String)hashMap.get("error"));
        }

    }
}
