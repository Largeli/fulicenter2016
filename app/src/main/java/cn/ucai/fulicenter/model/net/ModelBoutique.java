package cn.ucai.fulicenter.model.net;

import android.content.Context;
import android.widget.ImageView;

import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.model.utils.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ModelBoutique implements IModelBoutique {
    @Override
    public void downloadBoutiqueList(Context context,  OnCompletionListener<BoutiqueBean[]> listener) {
        OkHttpUtils<BoutiqueBean[]> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)
                .targetClass(BoutiqueBean[].class)
                .execute(listener);
    }

    @Override
    public void downloadboutiqueImage(Context context, String imageurl, int defaultPictureId, ImageView imageView) {
        ImageLoader.build(I.SERVER_ROOT+I.REQUEST_DOWNLOAD_IMAGE)
                .addParam(I.IMAGE_URL,imageurl)
                .defaultPicture(defaultPictureId)
                .imageView(imageView)
                .showImage(context);
    }
}
