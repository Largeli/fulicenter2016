package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
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
}
