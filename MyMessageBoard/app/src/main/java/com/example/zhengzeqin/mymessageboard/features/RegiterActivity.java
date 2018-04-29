package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.handler.RegisterHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;
import com.example.zhengzeqin.mymessageboard.utils.UserInfoUtil;

import java.util.HashMap;

public class RegiterActivity extends BaseActivity {

    public EditText phoneView;
    public EditText passwordView;
    public EditText codeView;
    public EditText userNameView;
    private RegisterHandler registerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
    }

    @Override
    public void configue() {
        super.configue();
        registerHandler = new RegisterHandler();
        this.registerHandler.setHandlerDelegate(new BaseHandler.BaseHandlerInterface() {

            @Override
            public void successHandler(HashMap hashMap, String tip) {
                ZQTool.ShowToastSip((tip.length() != 0)?tip:"注册成功",RegiterActivity.this);
                int authority = (Integer) hashMap.get("authority");
                UserModel userModel = (UserModel)hashMap.get("userModel");
                UserInfoUtil.setUserModel(null,RegiterActivity.this);
                UserInfoUtil.setUserModel(userModel,RegiterActivity.this);
                if (authority == 1){//登录成功是管理者则进入管理界面
                    ZQTool.NavigationToActivity(RegiterActivity.this,UsersManagerCenterActivity.class,RegiterActivity.this.getPushMap("Register"));
                }else if (authority == -1 || authority == 0){
                    //普通会员  //黑名单 游客
                    ZQTool.NavigationToActivity(RegiterActivity.this,MessageCenterActivity.class,RegiterActivity.this.getPushMap("Register"));
                }
                ZQTool.ShowToastSip((tip.length() != 0)?tip:"登录成功",RegiterActivity.this);
            }

            @Override
            public void failHandler(HashMap hashMap, String error) {
                ZQTool.ShowToastSip((error.length() != 0)?error:"注册失败",RegiterActivity.this);
            }
        });
    }

    //UI
    public void initUI(){
        navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("注册");
        phoneView = findViewById(R.id.phone);
        passwordView = findViewById(R.id.password);
        codeView = findViewById(R.id.code_num);
        userNameView = findViewById(R.id.user_name);

    }

    //action
    public void regsiterAction(View view) {
        String account = this.phoneView.getText().toString();
        String password = this.passwordView.getText().toString();
        String code = this.codeView.getText().toString();
        String userName = this.userNameView.getText().toString();

        if (userName.length() == 0){
            ZQTool.ShowToastSip("用户名不能为空",this);
            return;
        }

        if (account.length() == 0){
            ZQTool.ShowToastSip("请输入您的手机号码",this);
            return;
        }

        if (account.length() != 11){
            ZQTool.ShowToastSip("请输入您的11位手机号码",this);
            return;
        }

        if (password.length() == 0){
            ZQTool.ShowToastSip("请输入您的密码",this);
            return;
        }

        if (code.length() == 0){
            ZQTool.ShowToastSip("请输入您的验证码",this);
            return;
        }

        registerHandler.registerActionHandler(userName,account,password,code);
    }

    public void getCodeAction(View view) {
        String account = this.phoneView.getText().toString();
        if (account.length() == 0){
            ZQTool.ShowToastSip("请输入您的手机号码",this);
            return;
        }

        if (account.length() != 11){
            ZQTool.ShowToastSip("请输入您的11位手机号码",this);
            return;
        }
        int code = ZQTool.getRandNum(1,999999);
        UserInfoUtil.getInstance().randNumCode = code;
        String codeStr = "验证码是:" + code;
        ZQTool.ShowToastSip(codeStr,this);
    }
}
