package com.example.zhengzeqin.mymessageboard.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by zhengzeqin on 2017/11/21.
 */

public class ZQTool {
    //显示吐司
    public static void ShowToastSip(String tip,Context context){
        System.out.print(tip);
        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
    }

    //日志
    public static void DLog(String logs){
        Log.d("Tool", logs);
    }

    //隐藏输入响应
    public static boolean IsShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //隐藏actionbar
    public static void HideActionBar(ActionBar actionBar){
        if (actionBar != null){
            actionBar.hide();
        }
    }

    //导航
    public static void NavigationToActivity(Context context,Class cls){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    public static void NavigationToActivity(Activity context, Class cls, HashMap map){
        Intent intent = new Intent(context,cls);
        Bundle bundle = new Bundle();
        bundle.putSerializable("map", map);
        intent.putExtras(bundle);
        context.startActivityForResult(intent,1);
    }

    public static void NavigationHomeActivity(Activity context)  {
        Class classActivity = null;
        try {
            classActivity = Class.forName("com.example.zhengzeqin.mymessageboard.features.HomeActivity");
            Intent intent = new Intent(context,classActivity);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    /**
     * desc:将16进制的数据转为数组
     * @param data
     * @return
     * modified:
     */
    public static byte[] StringToBytes(String data){
        String hexString=data.toUpperCase().trim();
        if (hexString.length()%2!=0) {
            return null;
        }
        byte[] retData=new byte[hexString.length()/2];
        for(int i=0;i<hexString.length();i++)
        {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch3;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch3 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch3 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch4;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch4 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char2 <='F')
                int_ch4 = hex_char2-55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch3+int_ch4;
            retData[i/2]=(byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }


    /**
     * desc:将数组转为16进制
     * @param bArray
     * @return
     * modified:
     */
    public static String BytesToHexString(byte[] bArray) {
        if(bArray == null){
            return null;
        }
        if(bArray.length == 0){
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }



}
