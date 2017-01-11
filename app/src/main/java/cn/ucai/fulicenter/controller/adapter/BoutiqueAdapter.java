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
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.IModelBoutique;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<BoutiqueBean> bqList;
    IModelBoutique mModel;

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> bqList, IModelBoutique mModel) {
        this.context = context;
        this.bqList = bqList;
        this.mModel = mModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(context);
        View view = layout.inflate(R.layout.item_boutique,null);
        return new BoutiqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder parentholder, int position) {
        BoutiqueViewHolder holder = (BoutiqueViewHolder) parentholder;
        holder.tv_Boutique_title.setText(bqList.get(position).getTitle());
        holder.tv_Boutique_name.setText(bqList.get(position).getName());
        holder.tv_Boutique_des.setText(bqList.get(position).getDescription());
        mModel.downloadboutiqueImage(context,bqList.get(position).getImageurl(),R.drawable.nopic,holder.iv_Boutique);
    }

    @Override
    public int getItemCount() {
        return bqList.size();
    }

    public void initData(ArrayList<BoutiqueBean> list) {
        if (bqList == null){
            return;
        }else {
            if (bqList.size()!=0){
                bqList.clear();
            }
            bqList.addAll(list);
            notifyDataSetChanged();
        }
    }

    class BoutiqueViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_Boutique;
        TextView tv_Boutique_title;
        TextView tv_Boutique_name;
        TextView tv_Boutique_des;
        public BoutiqueViewHolder(View itemView) {
            super(itemView);
            iv_Boutique = (ImageView) itemView.findViewById(R.id.iv_Buotique);
            tv_Boutique_title = (TextView) itemView.findViewById(R.id.tv_Boutique_title);
            tv_Boutique_name = (TextView) itemView.findViewById(R.id.tv_Boutique_name);
            tv_Boutique_des = (TextView) itemView.findViewById(R.id.tv_Boutique_des);
        }
    }
}
