package com.example.zhengzeqin.mymessageboard.handler;

import android.content.ContentValues;
import android.view.View;

import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;

import org.litepal.crud.DataSupport;

import java.util.HashMap;

/**
 * Created by zhengzeqin on 2017/11/30.
 */

public class UsersManagerCenterHandler extends BaseHandler {
    //黑明单处理
    public void oprationBlackNameAction(UserModel model, int position,View btn){
        HashMap<String,Object> hashMap = new HashMap();
        hashMap.put("operation",-1);
        if (model != null && model instanceof UserModel){
            /*-1: 普通会员  1：管理者  0：黑名单*/
            int authority = model.getAuthority();
            if (authority == -1 || authority == 0){
                //添加黑名单
                ContentValues values = new ContentValues();
                values.put("authority", authority == -1?0:-1);
                int operationSuccess = DataSupport.update(UserModel.class, values, model.getId());
                if (operationSuccess != 0){
                    //黑名单处理成功
                    hashMap.put("status","1");
                    hashMap.put("position",position);
                    getHandlerDelegate().successHandler(hashMap,authority == -1?"已加黑名单":"去掉黑名单");
                    model.setAuthority(authority == -1?0:-1);
//                    if (btn instanceof Button){
//                        Button button = (Button) btn;
//                        button.setText(authority == -1?"去掉黑名单":"添加黑名单");
//                    }

                }else{
                    hashMap.put("error","黑名单处理失败");
                    hashMap.put("status","0");
                    getHandlerDelegate().failHandler(hashMap,"黑名单数据处理失败");
                }
            }else {
                //管理员
                hashMap.put("status","0");
                getHandlerDelegate().failHandler(hashMap,"我是管理者");
            }
        }
    }

    //移除
    public void oprationCancleUserAction(UserModel model,int position, View btn){
        //移除数据库
        if (model != null && model instanceof UserModel){
            int deleteSuccess = DataSupport.delete(UserModel.class,model.getId());
            HashMap<String,Object> hashMap = new HashMap();
            hashMap.put("operation",1);
            if (deleteSuccess != 0){
                //删除成功
                hashMap.put("status","1");
                hashMap.put("position",position);
                getHandlerDelegate().successHandler(hashMap,"删除成功");
            }else{
                hashMap.put("error","删除数据库失败");
                hashMap.put("status","0");
                getHandlerDelegate().failHandler(hashMap,"删除数据库失败");
            }
        }
    }

}
