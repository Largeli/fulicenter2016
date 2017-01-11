package cn.ucai.fulicenter.model.net;

import android.content.Context;
import android.widget.ImageView;

import cn.ucai.fulicenter.model.bean.BoutiqueBean;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelBoutique {
    void downloadBoutiqueList(Context context , OnCompletionListener<BoutiqueBean[]> listener);
    void downloadboutiqueImage(Context context , String imageurl,int defaultPictureId, ImageView imageView);
}
