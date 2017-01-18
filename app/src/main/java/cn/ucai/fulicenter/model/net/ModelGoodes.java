package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class ModelGoodes implements IModelGoodes {
    @Override
    public void downloadList(Context context, int goodsId, OnCompletionListener<GoodsDetailsBean> listener) {
        OkHttpUtils<GoodsDetailsBean> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)
                .addParam(I.GoodsDetails.KEY_GOODS_ID,""+goodsId)
                .targetClass(GoodsDetailsBean.class)
                .execute(listener);
    }

    @Override
    public void isCollect(Context context, int goodsId, String username, OnCompletionListener<MessageBean> listener) {
        OkHttpUtils<MessageBean> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_IS_COLLECT)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .addParam(I.Collect.USER_NAME,username)
                .targetClass(MessageBean.class)
                .execute(listener);
    }

    @Override
    public void setCollect(Context context, int goodsId, String username, int action, OnCompletionListener<MessageBean> listener) {
        OkHttpUtils<MessageBean> utils = new OkHttpUtils<>(context);
        String url = I.REQUEST_ADD_COLLECT;
        if (action == I.ACTION_DELETE_COLLECT) {
            url = I.REQUEST_DELETE_COLLECT;
        }
        utils.setRequestUrl(url)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .addParam(I.Collect.USER_NAME,username)
                .targetClass(MessageBean.class)
                .execute(listener);
    }
}
