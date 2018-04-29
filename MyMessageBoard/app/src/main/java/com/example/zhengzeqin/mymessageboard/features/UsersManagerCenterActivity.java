package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.adapter.UserCenterAdapter;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.base.BaseArrayAdapter;
import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.customview.NavigationBar;
import com.example.zhengzeqin.mymessageboard.handler.UsersManagerCenterHandler;
import com.example.zhengzeqin.mymessageboard.model.UserModel;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersManagerCenterActivity extends BaseActivity {

    private UsersManagerCenterHandler usersManagerCenterHandler;
    private ListView listView;
    private List<UserModel> userModelList = new ArrayList<>();
    private UserCenterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manager_center);
        initUI();

    }

    @Override
    public void configue() {
        super.configue();
        usersManagerCenterHandler = new UsersManagerCenterHandler();
        usersManagerCenterHandler.setHandlerDelegate(new BaseHandler.BaseHandlerInterface(){
            @Override
            public void successHandler(HashMap hashMap, String tip) {
                int operation = (int )hashMap.get("operation");
                if (operation == 1){//删除
                    //删除成功
                    int position = (int)hashMap.get("position");
                    UsersManagerCenterActivity.this.deletePosition(position);
                    ZQTool.ShowToastSip((tip.length() != 0)?tip:"删除成功",UsersManagerCenterActivity.this);
                }else if(operation == -1){//添加黑名单 //去掉黑名单
                    ZQTool.ShowToastSip((tip.length() != 0)?tip:"黑名单处理成功",UsersManagerCenterActivity.this);
                    adapter.notifyDataSetInvalidated();
                }

            }

            @Override
            public void failHandler(HashMap hashMap, String error) {
                ZQTool.ShowToastSip((error.length() != 0)?error:"数据操作失败",UsersManagerCenterActivity.this);
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refreshUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshUI();
    }

    public void loadData(){
        List<UserModel> userModelList = DataSupport.findAll(UserModel.class);
        this.userModelList = userModelList;
    }

    public void refreshUI(){
        loadData();
        adapter = new UserCenterAdapter(this, R.layout.user_center_item, userModelList);
        listView.setAdapter(adapter);
        adapter.setBtnClickLister(new BaseArrayAdapter.BaseArrayAdapterClickInterface(){
            @Override
            public void onClick(Object object ,int position,View v) {
                UsersManagerCenterActivity.this.btnClick(object,position,v);
            }

        });

    }

    /*UI*/
    public void initUI(){
        navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("会员管理中心");
        navigationBar.setShowRightBtn(true);
        navigationBar.setRightBtnTitle("消息中心");
        navigationBar.setRigthBtnFontSize(10);
        navigationBar.setActionDelegate(new NavigationBar.NavigationActionInterface(){
            @Override
            public void leftBtnAction(View view) {
                String formType = UsersManagerCenterActivity.this.fromType();
                if (formType.equals("Login") || formType.equals("Register")  ){
                    ZQTool.NavigationHomeActivity(UsersManagerCenterActivity.this);
                }else {
                    UsersManagerCenterActivity.this.finish();
                }
            }

            @Override
            public void rightBtnAction(View view) {
                //去留言
                ZQTool.NavigationToActivity(UsersManagerCenterActivity.this,MessageCenterActivity.class);
            }
        });

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                UserModel userModel = userModelList.get(position);

            }
        });
    }

    private void btnClick(Object object,int position,View v){
        switch (v.getId()){
            case R.id.blackbtn:{
//                        ZQTool.ShowToastSip("blackbtn",UsersManagerCenterActivity.this);
                UserModel model = (UserModel)object;
                usersManagerCenterHandler.oprationBlackNameAction(model,position,v);
            }
            break;
            case R.id.deletebtn:{
//                        ZQTool.ShowToastSip("deletebtn",UsersManagerCenterActivity.this);
                UserModel model = (UserModel)object;
                usersManagerCenterHandler.oprationCancleUserAction(model,position,v);
            }
            break;
        }
    }


    public void deletePosition(int position) {
        userModelList.remove(position);
        adapter.notifyDataSetInvalidated();
    }


}

