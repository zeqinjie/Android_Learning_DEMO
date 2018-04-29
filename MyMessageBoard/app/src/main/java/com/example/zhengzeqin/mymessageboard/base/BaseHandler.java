package com.example.zhengzeqin.mymessageboard.base;

import java.util.HashMap;

/**
 * Created by zhengzeqin on 2017/11/26.
 */

public class BaseHandler {

    private  BaseHandlerInterface handlerDelegate;

    public BaseHandlerInterface getHandlerDelegate() {
        return handlerDelegate;
    }

    public void setHandlerDelegate(BaseHandlerInterface handlerDelegate) {
        this.handlerDelegate = handlerDelegate;
    }

    /*interface*/
    public interface BaseHandlerInterface {

        public void successHandler(HashMap hashMap,String tip);
        public void failHandler(HashMap hashMap,String error);
    }

}
