package cn.ucai.fulicenter.model.net;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public interface IModelUser {
     void login(Context context, String username, String password,OnCompletionListener<String> listener);
     void register(Context context, String username,String usernick, String password,OnCompletionListener<String> listener);
    void updatNick(Context context,String username,String usernick,OnCompletionListener<String> listener);
    void uploadAvatar(Context context, String username, File file, OnCompletionListener<String> listener);
}

