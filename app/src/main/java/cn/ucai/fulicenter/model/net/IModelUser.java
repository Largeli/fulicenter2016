package cn.ucai.fulicenter.model.net;

import android.content.Context;

import java.io.File;

import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.CollectBean;
import cn.ucai.fulicenter.model.bean.MessageBean;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public interface IModelUser {
     void login(Context context, String username, String password,OnCompletionListener<String> listener);
     void register(Context context, String username,String usernick, String password,OnCompletionListener<String> listener);
    void updatNick(Context context,String username,String usernick,OnCompletionListener<String> listener);
    void uploadAvatar(Context context, String username, File file, OnCompletionListener<String> listener);
    void collectCount(Context context,String username, OnCompletionListener<MessageBean> listener);
    void getcollect(Context context, String username, int pageId, int pageSize, OnCompletionListener<CollectBean[]> listener);
    void getCart(Context context, String username, OnCompletionListener<CartBean[]> listener);
  //  void addCart(Context context, String username,int goodsId,int count , OnCompletionListener<MessageBean> listener);
  //  void deltCart(Context context, int cartId, OnCompletionListener<MessageBean> listener);
   // void updateCart(Context context,int cartId , OnCompletionListener<MessageBean> listener);
    void updateCart(Context context,int action, String username, int goodsId,int count,int cartId , OnCompletionListener<MessageBean> listener);

}

