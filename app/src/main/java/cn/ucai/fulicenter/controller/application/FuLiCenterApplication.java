package cn.ucai.fulicenter.controller.application;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;
import cn.ucai.fulicenter.model.bean.User;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class FuLiCenterApplication extends Application {
    private static FuLiCenterApplication intance;
    public static FuLiCenterApplication getInstance(){
        return intance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intance = this;
        ShareSDK.initSDK(this);
    }
    private static User user;




    public static User getUser() {
        return user;
    }

    public static void removeUser(){
        FuLiCenterApplication.user = null;
    }
    public static void setUser(User user) {
        FuLiCenterApplication.user = user;
    }
}
