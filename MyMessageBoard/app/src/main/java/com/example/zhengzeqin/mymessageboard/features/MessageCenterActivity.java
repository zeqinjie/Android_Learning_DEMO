package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.adapter.MessageCenterAdapter;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.customview.NavigationBar;
import com.example.zhengzeqin.mymessageboard.model.MessageModel;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageCenterActivity extends BaseActivity {

    private ListView listView;
    private List<MessageModel> messageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        initUI();
    }

    @Override
    public void configue() {
        super.configue();

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
        List<MessageModel> messageList = DataSupport.findAll(MessageModel.class);
        Collections.reverse(messageList);
        this.messageList = messageList;
    }

    public void refreshUI(){
        loadData();
        MessageCenterAdapter adapter = new MessageCenterAdapter(this, R.layout.message_center_item, messageList);
        listView.setAdapter(adapter);
    }

    /*UI*/
    public void initUI(){
        NavigationBar navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("留言消息中心");
        navigationBar.setShowRightBtn(true);
        navigationBar.setRightBtnTitle("去留言");
        navigationBar.setActionDelegate(new NavigationBar.NavigationActionInterface(){
            @Override
            public void leftBtnAction(View view) {
                String formType = MessageCenterActivity.this.fromType();
                if (formType.equals("Login") || formType.equals("Register")  ){
                    ZQTool.NavigationHomeActivity(MessageCenterActivity.this);
                }else {
                    MessageCenterActivity.this.finish();
                }
            }

            @Override
            public void rightBtnAction(View view) {
                //去留言
                if (MessageCenterActivity.this.isLogin){//留言板
                    ZQTool.NavigationToActivity(MessageCenterActivity.this,MessageBoardActivity.class);
                }else{//提示去登录
                    ZQTool.ShowToastSip("留言请登录...",MessageCenterActivity.this);
                }
            }
        });
        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                MessageModel messageModel = messageList.get(position);

            }
        });
    }



}
