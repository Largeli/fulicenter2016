package cn.ucai.fulicenter.model.net;

import android.content.Context;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public interface IModelUser {
    public void login(Context context, String username, String password,OnCompletionListener<String> listener);
    public void register(Context context, String username,String usernick, String password,OnCompletionListener<String> listener);
}
