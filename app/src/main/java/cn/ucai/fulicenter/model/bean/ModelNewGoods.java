package cn.ucai.fulicenter.model.bean;

import android.content.Context;
import android.widget.ImageView;

import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.net.IModelNewGoods;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ModelNewGoods implements IModelNewGoods {

    @Override
    public void downloadContactList(Context context, int cartIn, int pageId, OnCompletionListener<NewGoodsBean[]> listener) {
        OkHttpUtils<NewGoodsBean[]> utils = new OkHttpUtils<>(context);
        if (cartIn == 0) {
            utils.setRequestUrl(I.REQUEST_FIND_NEW_BOUTIQUE_GOODS)
                    .addParam(I.NewAndBoutiqueGoods.CAT_ID,""+cartIn)
                    .addParam(I.PAGE_ID,pageId+"")
                    .addParam(I.PAGE_SIZE,10+"")
                    .targetClass(NewGoodsBean[].class)
                    .execute(listener);
        }else {
            utils.setRequestUrl(I.REQUEST_FIND_GOODS_DETAILS)
                    .addParam(I.NewAndBoutiqueGoods.CAT_ID,""+cartIn)
                    .addParam(I.PAGE_ID,pageId+"")
                    .addParam(I.PAGE_SIZE,10+"")
                    .targetClass(NewGoodsBean[].class)
                    .execute(listener);
        }

    }

    @Override
    public void downloadnewgoodsImage(Context context, String imageurl, int defaultPictureId,ImageView imageView) {
       /* OkImageLoader.build(I.SERVER_ROOT + I.REQUEST_DOWNLOAD_IMAGE)
                .addParam(I.IMAGE_URL, imageurl)
                .imageView(imageView)
                .showImage(context);*/
        ImageLoader.build(I.SERVER_ROOT+I.REQUEST_DOWNLOAD_IMAGE)
                .addParam(I.IMAGE_URL,imageurl)
                .imageView(imageView)
                .defaultPicture(defaultPictureId)
                .showImage(context);
    }
}
