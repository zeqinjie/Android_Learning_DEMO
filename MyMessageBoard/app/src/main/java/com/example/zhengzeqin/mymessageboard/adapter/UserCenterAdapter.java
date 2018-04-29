package com.example.zhengzeqin.mymessageboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseArrayAdapter;
import com.example.zhengzeqin.mymessageboard.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by zhengzeqin on 2017/11/30.
 */

public class UserCenterAdapter  extends BaseArrayAdapter {


//    private Button blackBtn;
//    private Button deleteBtn;

    private List<UserModel> userModels = new ArrayList<>();
    public UserCenterAdapter(@NonNull Context context, int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);
        userModels = objects;
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int i) {
        return userModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        UserModel userModel = (UserModel)getItem(position); // 获取当前项的Fruit实例
        View view;
        UserCenterAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new UserCenterAdapter.ViewHolder();
            viewHolder.context = (TextView) view.findViewById (R.id.context);
            viewHolder.blackbtn = (Button) view.findViewById (R.id.blackbtn);
            viewHolder.deletebtn = (Button) view.findViewById (R.id.deletebtn);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (UserCenterAdapter.ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        int userid = userModel.getId();
        Integer authority = userModel.getAuthority();
        String userName = userModel.getUserName();
        String account = userModel.getUserAccount();
        String password = userModel.getUserPassword();

        if (userName == null) userName = "匿名";
        if (account == null) account = "未知账户";
        if (password == null) password = "未知密码";

        String context = "权限:" +  authorityStr(authority)+" 用户名:"+ userName+" 手机号:"+account;
        viewHolder.context.setText(context);
        viewHolder.blackbtn.setVisibility(VISIBLE);
         /*-1: 普通会员  1：管理者  0：黑名单*/
        if (authority == 1){
            viewHolder.blackbtn.setVisibility(INVISIBLE);
        }else if (authority == -1){
            viewHolder.blackbtn.setText("添加黑名单");
        }else if (authority == 0){
            viewHolder.blackbtn.setText("去掉黑名单");
        }
        viewHolder.blackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel = null;
                if (userModels != null){
                    userModel = userModels.get(position);
                }
                UserCenterAdapter.this.getBtnClickLister().onClick(userModel,position,v);
            }
        });

        viewHolder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel = null;
                if (userModels != null){
                    userModel = userModels.get(position);
                }
                UserCenterAdapter.this.getBtnClickLister().onClick(userModel,position,v);
            }
        });

        return view;
    }

    public String authorityStr(int authority){
        String str = "会员";
        if (authority == 1){
            str = "管理员";
        }else if (authority == 0){
            str = "黑名单";
        }
        return str;
    }


    class ViewHolder {

        TextView context;
        Button blackbtn;
        Button deletebtn;

    }
}
