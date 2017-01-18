package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.MessageBean;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public interface IModelGoodes {
    void downloadList(Context context, int goodsId, OnCompletionListener<GoodsDetailsBean> listener);
    void isCollect(Context context, int goodsId, String username, OnCompletionListener<MessageBean> listener);
}
