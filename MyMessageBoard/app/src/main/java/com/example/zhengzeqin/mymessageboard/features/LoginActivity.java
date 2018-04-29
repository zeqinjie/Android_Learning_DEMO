package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.customview.NavigationBar;
import com.example.zhengzeqin.mymessageboard.handler.LoginHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;
import com.example.zhengzeqin.mymessageboard.utils.UserInfoUtil;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private AutoCompleteTextView accountView;
    private EditText passwordView;
    private LoginHandler loginHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    @Override
    public void configue() {
        super.configue();
        this.loginHander = new LoginHandler();
        this.loginHander.setHandlerDelegate(new BaseHandler.BaseHandlerInterface() {
            @Override
            public void successHandler(HashMap hashMap,String tip) {
                int authority = (Integer) hashMap.get("authority");
                UserModel userModel = (UserModel)hashMap.get("userModel");
                UserInfoUtil.setUserModel(null,LoginActivity.this);
                UserInfoUtil.setUserModel(userModel,LoginActivity.this);
                if (authority == 1){//登录成功是管理者则进入管理界面
                    ZQTool.NavigationToActivity(LoginActivity.this,UsersManagerCenterActivity.class,LoginActivity.this.getPushMap("Login"));
                }else if (authority == -1 || authority == 0){
                    //普通会员  //黑名单 游客
                    ZQTool.NavigationToActivity(LoginActivity.this,MessageCenterActivity.class,LoginActivity.this.getPushMap("Login"));
                }
                ZQTool.ShowToastSip((tip.length() != 0)?tip:"登录成功",LoginActivity.this);
            }

            @Override
            public void failHandler(HashMap object, String error) {
                ZQTool.ShowToastSip((error.length() != 0)?error:"登录失败",LoginActivity.this);
            }
        });
    }

    /*UI*/
    public void initUI(){
        NavigationBar navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("登录");
        this.accountView = findViewById(R.id.account);
        this.passwordView = findViewById(R.id.password);
    }


    /*Action*/
    //注册
    public void registerAction(View view) {
        ZQTool.NavigationToActivity(this,RegiterActivity.class);
    }

    //登录
    public void loginAction(View view) {
        String account = this.accountView.getText().toString();
        String password = this.passwordView.getText().toString();
        if (account.length() == 0){
            ZQTool.ShowToastSip("请输入您的账號",this);
            return;
        }

        if (password.length() == 0){
            ZQTool.ShowToastSip("请输入您的密码",this);
            return;
        }
        loginHander.loginActionHandler(account,password);
    }
    /*funtion*/



//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.traceroute_rootview:
//                InputMethodManager imm = (InputMethodManager)
//                        getSystemService(this.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                break;
//        }
//
//    }


}

