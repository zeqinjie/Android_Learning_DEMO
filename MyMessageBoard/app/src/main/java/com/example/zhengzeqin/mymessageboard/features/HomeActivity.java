package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.customview.NavigationBar;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    @Override
    public void configue() {
        super.configue();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setRightBtn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setRightBtn();
    }

    /*UI*/
    public void initUI(){
        navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("功能平台");
        navigationBar.setHideLeftBtn(true);
        navigationBar.setShowRightBtn(true);

        navigationBar.setActionDelegate(new NavigationBar.NavigationActionInterface() {
            @Override
            public void leftBtnAction(View view) {

            }

            @Override
            public void rightBtnAction(View view) {
                if (HomeActivity.this.isLogin){//退出
                    HomeActivity.this.isLogin = false;
                    HomeActivity.this.setUserModel(null);
                    setRightBtn();
                    ZQTool.ShowToastSip("已退出",HomeActivity.this);
                }else {//登录
                    ZQTool.NavigationToActivity(HomeActivity.this,LoginActivity.class);
                    loginOut();
                }
            }
        });

    }

    private void setRightBtn(){
        if (!this.isLogin){
            navigationBar.setRightBtnTitle("登录");
        }else{
            navigationBar.setRightBtnTitle("退出");
        }
    }

    /*Action*/
    public void messageCenterClick(View view) {
        ZQTool.NavigationToActivity(this,MessageCenterActivity.class);
    }

    public void userManagerCenterClick(View view) {

        if (isLogin){
            if (this.userModel.getAuthority() == 1){//管理员
                ZQTool.NavigationToActivity(this,UsersManagerCenterActivity.class);
            }else {
                ZQTool.ShowToastSip("非管理員權限",this);
            }
        }else {
            ZQTool.ShowToastSip("请登录...",this);
        }
    }


}
