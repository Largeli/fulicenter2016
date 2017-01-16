package cn.ucai.fulicenter.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.activity.BoutiqueChildActivity;
import cn.ucai.fulicenter.controller.activity.CategoryChildActivity;
import cn.ucai.fulicenter.controller.activity.GoodsDetailsActivity;
import cn.ucai.fulicenter.controller.activity.LoginActivity;
import cn.ucai.fulicenter.controller.activity.RegisterActivity;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {

    public static void finish(Activity context){
        context.finish();
        context.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
    public static void startActivity(Activity context,Class<?> clz){
        context.startActivity(new Intent(context,clz));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public static void startActivity(Activity context,Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoBoutiqueChildActivity(Context mContext, BoutiqueBean bean) {
        Intent intent = new Intent(mContext, BoutiqueChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,bean.getId())
                .putExtra(I.Boutique.TITLE,bean.getTitle());
        startActivity((Activity) mContext,intent);
    }
    public static void gotoGoodsDetail(Context context,int goodId){
        Intent intent = new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(I.GoodsDetails.KEY_GOODS_ID,goodId);
        startActivity((Activity)context,intent);
    }
    public static void gotoCategoryChild(Context context, String title, int catId, ArrayList<CategoryChildBean> list){
        Intent intent = new Intent(context, CategoryChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,catId);
        intent.putExtra(I.CategoryChild.NAME,title);
        intent.putExtra(I.CategoryChild.DATA,list);
        startActivity((Activity)context,intent);
    }

    public static void gotoLogin(Context context) {
        startActivity((Activity)context,LoginActivity.class);
    }

    public static void gotoRegister(LoginActivity loginActivity) {
        startActivity(loginActivity, RegisterActivity.class);
    }
}
