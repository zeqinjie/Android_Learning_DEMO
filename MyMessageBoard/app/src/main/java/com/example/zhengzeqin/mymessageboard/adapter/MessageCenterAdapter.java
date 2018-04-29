package com.example.zhengzeqin.mymessageboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhengzeqin.mymessageboard.R;
import com.example.zhengzeqin.mymessageboard.base.BaseArrayAdapter;
import com.example.zhengzeqin.mymessageboard.model.MessageModel;
import com.example.zhengzeqin.mymessageboard.tool.DateTool;

import java.util.Date;
import java.util.List;


/**
 * Created by zhengzeqin on 2017/11/30.
 */

public class MessageCenterAdapter extends BaseArrayAdapter {



    public MessageCenterAdapter(@NonNull Context context, int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MessageModel messageModel = (MessageModel)getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById (R.id.title);
            viewHolder.context = (TextView) view.findViewById (R.id.context);
            viewHolder.sub_context = (TextView) view.findViewById (R.id.sub_context);
            view.setTag(viewHolder); // 将ViewHolder存储在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        String title = messageModel.getTitle();
        String context = messageModel.getContext();
        if (title == null) title = "暂无标题";
        if (context == null) context = "暂无内容";
        viewHolder.title.setText("标题: "+title);
        viewHolder.context.setText("内容: "+context);
        viewHolder.sub_context.setText(sub_context(messageModel));
        return view;
    }

    private String sub_context(MessageModel model){
        String context = "";
        Date date = model.getPublishDate();
        String userName = model.getUserName();
        if (userName == null) userName = "匿名";
        if (date == null) date = new Date();
        context = "用户:" +"   " + userName + "   " + DateTool.fromToday(date);
        return context;
    }

    class ViewHolder {

        TextView title;

        TextView context;

        TextView sub_context;

    }
}
