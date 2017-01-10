package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.net.IModelContact;
import cn.ucai.fulicenter.model.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class NewGoodsAdapter extends RecyclerView.Adapter{
    static final int TYPE_ITEM = 0;
    static final int TYPE_FOOTER = 1;
    ArrayList<NewGoodsBean> arrayList;
    Context context;
    String footer;
    boolean isMore;
    boolean isDragging;
    IModelContact model;
    public NewGoodsAdapter(ArrayList<NewGoodsBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View  layout ;
        switch (viewType) {
            case TYPE_FOOTER:
                layout = layoutInflater.inflate(R.layout.item_newgoods_footer,null);
                return new FooterViewHolder(layout);
            case TYPE_ITEM:
                layout = layoutInflater.inflate(R.layout.item_newgoods_contact,null);
                return new ContactViewHolder(layout);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        if (position == getItemCount() - 1) {
            FooterViewHolder holder = (FooterViewHolder) parentholder;
            holder.tvfooter.setText(getFooter());
            return;
        }
        ContactViewHolder holder = (ContactViewHolder) parentholder;
       NewGoodsBean contact = arrayList.get(position);
        holder.tvName.setText(contact.getGoodsName());
        holder.tvMuch.setText(contact.getCurrencyPrice());
        //model.downloadNewgoodsList(context,contact.getGoodsName(),contact.);
        OkImageLoader.build(I.SERVER_ROOT+I.REQUEST_DOWNLOAD_IMAGE)
                .addParam(I.IMAGE_URL,contact.getGoodsImg())
                .defaultPicture(R.drawable.nopic)
                .imageView(holder.ivAvatar)
                .showImage(context);
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){return TYPE_FOOTER;}
        return TYPE_ITEM;
    }

    class FooterViewHolder  extends RecyclerView.ViewHolder {
        TextView tvfooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tvfooter = (TextView) itemView.findViewById(R.id.tv_newgoods_footer);
        }
    }
    class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
        TextView tvMuch;
        public ContactViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_Newgood_Avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_Newgood_Name);
            tvMuch = (TextView) itemView.findViewById(R.id.tv_Newgood_much);
        }
    }
}
