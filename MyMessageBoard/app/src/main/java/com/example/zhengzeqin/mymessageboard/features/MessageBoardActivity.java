package com.example.zhengzeqin.mymessageboard.features;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseActivity;
import com.example.zhengzeqin.mymessageboard.base.BaseHandler;
import com.example.zhengzeqin.mymessageboard.handler.MessageBoradHandler;
import com.example.zhengzeqin.mymessageboard.tool.ZQTool;

import java.util.HashMap;

public class MessageBoardActivity extends BaseActivity {

    private EditText contextView;
    private AutoCompleteTextView titleView;
    private MessageBoradHandler messageBoradHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);
        initUI();
    }

    @Override
    public void configue() {
        super.configue();
        messageBoradHandler = new MessageBoradHandler();
        messageBoradHandler.setHandlerDelegate(new BaseHandler.BaseHandlerInterface(){
            @Override
            public void successHandler(HashMap hashMap, String tip) {
                ZQTool.ShowToastSip((tip.length() != 0)?tip:"留言成功",MessageBoardActivity.this);
            }

            @Override
            public void failHandler(HashMap hashMap, String error) {
                ZQTool.ShowToastSip((error.length() != 0)?error:"发送失败",MessageBoardActivity.this);
            }
        });
    }

    /*UI*/
    public void initUI(){
        navigationBar = findViewById(R.id.naviagtionbar);
        navigationBar.setTitle("留言板");
        this.contextView = findViewById(R.id.context_view);
//        this.contextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.titleView = findViewById(R.id.title_view);

    }

    //action
    public void sendClick(View view) {
        String title = this.titleView.getText().toString();
        String context = this.contextView.getText().toString();
        if (title.length() == 0){
            ZQTool.ShowToastSip("请输入您的标题",this);
            return;
        }

        if (context.length() == 0){
            ZQTool.ShowToastSip("请输入您的内容",this);
            return;
        }
        messageBoradHandler.sendMessage(title,context,getCurrentUserModel());
    }
}
