package com.example.zhengzeqin.mymessageboard.utils;

import android.content.Context;

import com.example.zhengzeqin.mymessageboard.model.UserModel;

/**
 * Created by zhengzeqin on 2017/11/26.
 */

public class UserInfoUtil {
    private final static String FILENAME = "save_object_file";
//
    private final static String KEY = "UserModel";

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static UserInfoUtil instance = null;

    public int randNumCode;


    public UserModel userModel;

    /* 私有构造方法，防止被实例化 */
    private UserInfoUtil() {
    }

    /* 1:懒汉式，静态工程方法，创建实例 */
    public static UserInfoUtil getInstance() {
        if (instance == null) {
            instance = new UserInfoUtil();
        }
        return instance;
    }


    public static void setUserModel(UserModel model, Context context){
        if (model != null){
            SharedPreferenceUtil.saveObject(FILENAME,KEY,model,context);
        }else{
            SharedPreferenceUtil.clearObject(FILENAME,KEY,context);
        }
    }

    public static UserModel getUserModel(Context context){
        Object object = SharedPreferenceUtil.getObject(FILENAME,KEY,context);
        if (object instanceof UserModel){
            UserModel model = (UserModel)object;
            return model;
        }
        return null;
    }

    public boolean isLogin(Context context){
        UserModel model = UserInfoUtil.getUserModel(context);
        if (model != null){
            return true;
        }
        return  false;
    }



//    public void rand(){
//        System.out.println("随机数为" + getRandNum(1,999999));
//    }


}
