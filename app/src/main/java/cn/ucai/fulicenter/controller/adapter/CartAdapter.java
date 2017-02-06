package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModelUser;
import cn.ucai.fulicenter.model.net.ModeUser;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.ImageLoader;

/**
 * Created by Administrator on 2017/1/20 0020.
 */

public class CartAdapter extends RecyclerView.Adapter {
    private static final String TAG = CartAdapter.class.getSimpleName();
    Context mContext;
    ArrayList<CartBean> mList;
    IModelUser model;
    User user;
    int listPosition;
    public CartAdapter(Context context, ArrayList<CartBean> mList) {
        mContext = context;
        this.mList = mList;
        model = new ModeUser();
        user = FuLiCenterApplication.getUser();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View layout = inflater.inflate(R.layout.item_cart, null);
        CartViewHolder vh = new CartViewHolder(layout);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder pholder, int position) {
        CartViewHolder holder =(CartViewHolder)pholder;
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mList!=null?mList.size():0;
    }

    @OnClick({ R.id.iv_cart_add, R.id.iv_cart_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cart_add:
                addCart();
                break;
            case R.id.iv_cart_del:
                delCart();
                break;
        }
    }

    private void delCart() {
        final int count = mList.get(listPosition).getCount();
        int action = I.ACTION_CART_UPDATA;
        if ( count > 1) {
            action = I.ACTION_CART_UPDATA;
        }   else {
            action = I.ACTION_CART_DEL;
        }
        model.updateCart(mContext, action, user.getMuserName(),
                mList.get(listPosition).getGoodsId(),count-1, mList.get(listPosition).getId(),
                new OnCompletionListener<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean result) {
                        if (result != null && result.isSuccess()) {
                            if (count <= 1) {
                                mList.remove(listPosition);
                            }else {
                                mList.get(listPosition).setCount(count-1);
                            }
                            mContext.sendBroadcast(new Intent(I.BROADCAST_UPDATA_CART));
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }

    private void addCart() {
        model.updateCart(mContext, I.ACTION_CART_ADD, user.getMuserName(),
                mList.get(listPosition).getGoodsId(), 1, mList.get(listPosition).getId(),
                new OnCompletionListener<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean result) {
                        if (result != null && result.isSuccess()) {
                            mList.get(listPosition).setCount(mList.get(listPosition).getCount()+1);
                            mContext.sendBroadcast(new Intent(I.BROADCAST_UPDATA_CART));
                        }
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
    }


    public void initData(ArrayList<CartBean> list) {
        if (mList != null) {
            mList.clear();
        }
        addData(list);
    }

    public void addData(ArrayList<CartBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_cart_avatar)
        ImageView ivCartAvatar;
        @BindView(R.id.tv_cart_title)
        TextView tvCartTitle;
        @BindView(R.id.tv_cart_number)
        TextView tvCartNumber;
        @BindView(R.id.tv_cart_price)
        TextView tvCartPrice;
        @BindView(R.id.cbx_cart)
        CheckBox mSelected;
        CartViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
         public void bind(int position) {
             listPosition = position;
             GoodsDetailsBean detailsBean = mList.get(position).getGoods();
           //  L.e(TAG,"detailsBean="+detailsBean);
             if (detailsBean != null) {
                 ImageLoader.downloadImg(mContext,ivCartAvatar,detailsBean.getGoodsThumb());
                 tvCartPrice.setText(detailsBean.getCurrencyPrice());
                 tvCartTitle.setText(detailsBean.getGoodsName());
             }
             tvCartNumber.setText("("+mList.get(position).getCount()+")");
             mSelected.setChecked(mList.get(listPosition).isChecked());
         }
        @OnCheckedChanged(R.id.cbx_cart)
        public void onChecked(boolean checked){
         //   L.e("main","onchecked");
            mList.get(listPosition).setChecked(checked);
         //   L.e("main","oncheckedzz");
            mContext.sendBroadcast(new Intent(I.BROADCAST_UPDATA_CART));
        }
     }
}
