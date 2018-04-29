package com.example.zhengzeqin.mymessageboard.customview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhengzeqin.mymessageboard.R;



/**
 * Created by zhengzeqin on 2017/11/20.
 */

public class NavigationBar extends LinearLayout implements View.OnClickListener {


    /*private pro*/
    private Context Context;
    private TextView titleView;
    private Button leftBtn;
    private Button rightBtn;
    private String title;
    private String rightBtnTitle;
    private String leftBtnTitle;
    private int rigthBtnFontSize;
    private NavigationActionInterface actionDelegate;

    /*public pro*/
    public Boolean isHideLeftBtn;
    public Boolean isShowRightBtn;

    /*cont*/
    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.Context = context;
        LayoutInflater.from(context).inflate(R.layout.navigationbar, this);
        initUI();
    }

    /*UI*/
    private void initUI(){
        Button leftBtn = findViewById(R.id.left_btn);
        this.leftBtn = leftBtn;
        leftBtn.setOnClickListener(this);

        TextView title = findViewById(R.id.title);
        this.titleView = title;

        Button rightBtn = findViewById(R.id.right_btn);
        this.rightBtn = rightBtn;
        rightBtn.setOnClickListener(this);
    }

    /*setter &&  getter*/
    public void setTitle(String title) {
        this.title = title;
        this.titleView.setText(title);
    }

    public void setRightBtnTitle(String rightBtnTitle) {
        this.rightBtnTitle = rightBtnTitle;
        this.rightBtn.setText(rightBtnTitle);
    }

    public void setLeftBtnTitle(String leftBtnTitle) {
        this.leftBtnTitle = leftBtnTitle;
        this.leftBtn.setText(leftBtnTitle);
    }

    public void setHideLeftBtn(Boolean hideLeftBtn) {
        isHideLeftBtn = hideLeftBtn;
        this.leftBtn.setVisibility(hideLeftBtn?INVISIBLE:VISIBLE);
    }

    public void setShowRightBtn(Boolean showRightBtn) {
        isShowRightBtn = showRightBtn;
        this.rightBtn.setVisibility(showRightBtn?VISIBLE:INVISIBLE);
    }

    public void setActionDelegate(NavigationActionInterface actionDelegate) {
        this.actionDelegate = actionDelegate;
    }

    public void setRigthBtnFontSize(int rigthBtnFontSize) {
        this.rigthBtnFontSize = rigthBtnFontSize;
        this.rightBtn.setTextSize(rigthBtnFontSize);
    }

    /*Interface*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_btn:{
                if (actionDelegate != null){
                    actionDelegate.leftBtnAction(v);
                }else {
                    ((Activity)NavigationBar.this.Context).finish();//销毁activit 返回
                }
            }
            break;
            case R.id.right_btn:{
                if (actionDelegate != null){
                    actionDelegate.rightBtnAction(v);
                }
            }
            break;

        }

    }

    /*interface*/
    public interface NavigationActionInterface {
        public void leftBtnAction(View view);
        public void rightBtnAction(View view);
    }
}
