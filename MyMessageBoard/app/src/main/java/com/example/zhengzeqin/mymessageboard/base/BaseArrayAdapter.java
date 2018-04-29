package com.example.zhengzeqin.mymessageboard.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * Created by zhengzeqin on 2017/11/30.
 */

public class BaseArrayAdapter extends ArrayAdapter {
    public int resourceId;
    public BaseArrayAdapterClickInterface btnClickLister;
    public BaseArrayAdapter(@NonNull Context context, int textViewResourceId, @NonNull List objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public void setBtnClickLister(BaseArrayAdapterClickInterface btnClickLister) {
        this.btnClickLister = btnClickLister;
    }

    public BaseArrayAdapterClickInterface getBtnClickLister() {
        return btnClickLister;
    }

    /*interface*/
    public interface BaseArrayAdapterClickInterface {

        public void onClick(@Nullable Object object, int position, View v) ;

    }

}
