package cn.ucai.fulicenter.view;

import android.app.Activity;
import android.content.Intent;

import cn.ucai.fulicenter.R;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    public static void startActivity(Activity context,Class<?> clr){
        context.startActivity(new Intent(context,clr));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void finshActivity(Activity context){
        context.finish();
    }
}