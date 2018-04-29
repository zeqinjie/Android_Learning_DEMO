package com.example.zhengzeqin.mymessageboard.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.zhengzeqin.mymessageboard.customview.NavigationBar;
import com.example.zhengzeqin.mymessageboard.model.UserModel;
import com.example.zhengzeqin.mymessageboard.myApplication.AppManager;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;
import com.example.zhengzeqin.mymessageboard.utils.UserInfoUtil;

import java.util.HashMap;

public class BaseActivity extends AppCompatActivity {

    public boolean isLogin;
    public UserModel userModel;
    public NavigationBar navigationBar;
//    public Intent intent = getIntent();
//    public String formType = formType();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        AppManager.getAppManager().addActivity(this);
        ZQTool.HideActionBar(this.getSupportActionBar());
        configue();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    public void configue(){
        this.isLogin = isLogin();
        if(isLogin)this.userModel = getCurrentUserModel();
    }

    /*setter && getter*/
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
        UserInfoUtil.setUserModel(userModel,this);
    }


    /*是否已经登录过*/
    private boolean isLogin(){
        boolean isLogin = UserInfoUtil.getInstance().isLogin(BaseActivity.this);
        return isLogin;
    }

    /*获取缓存的对象  静态方法不能使用this 实例对象*/
    public UserModel getCurrentUserModel(){
        UserModel model = UserInfoUtil.getUserModel(BaseActivity.this);
        return model;
    }

    //登出
    public void loginOut(){
        UserInfoUtil.setUserModel(null,this);
        this.isLogin = false;
    }

    public String fromType(){
        Intent intent = getIntent();
        String fromType = "";
        if (intent != null){
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) return fromType ;
            HashMap map=(HashMap)bundle.getSerializable("map");
            if(map != null && !map.isEmpty()) {
                fromType = (String)map.get("fromType");
                if (fromType == null) fromType = "";
            }

        }
        return fromType;
    }

    public HashMap getPushMap(String formType){
        HashMap<String,String> sendMap = new HashMap();
        sendMap.put("fromType",formType);
        return sendMap;
    }

    /*事件派发*/
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (ZQTool.IsShouldHideInput(v, ev)) {
                //收键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
