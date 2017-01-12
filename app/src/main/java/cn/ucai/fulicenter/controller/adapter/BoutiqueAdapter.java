package cn.ucai.fulicenter.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.IModelBoutique;
import cn.ucai.fulicenter.model.utils.L;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class BoutiqueAdapter extends RecyclerView.Adapter<BoutiqueAdapter.BoutiqueViewHolder> {
    Context context;
    ArrayList<BoutiqueBean> bqList;
    IModelBoutique mModel;

    public BoutiqueAdapter(Context context, ArrayList<BoutiqueBean> bqList, IModelBoutique mModel) {
        this.context = context;
        this.bqList = bqList;
        this.mModel = mModel;
    }

    @Override
    public BoutiqueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(context);
        View view = layout.inflate(R.layout.item_boutique, null);
        return new BoutiqueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoutiqueViewHolder holder, int position) {
        holder.tv_Boutique_title.setText(bqList.get(position).getTitle());
        holder.tv_Boutique_name.setText(bqList.get(position).getName());
        holder.tv_Boutique_des.setText(bqList.get(position).getDescription());
        mModel.downloadboutiqueImage(context, bqList.get(position).getImageurl(), R.drawable.nopic, holder.iv_Boutique);
    }

    @Override
    public int getItemCount() {
        return bqList == null ? 0 : bqList.size();
    }

    public void initData(ArrayList<BoutiqueBean> list) {
        if (bqList != null) {
            bqList.clear();
        }
        bqList.addAll(list);
        L.e("main", "initData");
        notifyDataSetChanged();
    }

    public void addData(ArrayList<BoutiqueBean> list) {
        bqList.addAll(list);
        L.e("main", "addData");
        notifyDataSetChanged();
    }

    class BoutiqueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_Buotique)
        ImageView iv_Boutique;
        @BindView(R.id.tv_Boutique_title)
        TextView tv_Boutique_title;
        @BindView(R.id.tv_Boutique_name)
        TextView tv_Boutique_name;
        @BindView(R.id.tv_Boutique_des)
        TextView tv_Boutique_des;

        public BoutiqueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
