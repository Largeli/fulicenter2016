package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.adapter.BoutiqueAdapter;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.net.IModelBoutique;
import cn.ucai.fulicenter.model.net.ModelBoutique;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.ConvertUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {


    @BindView(R.id.tvRefreshHint)
    TextView tvRefreshHint;
    @BindView(R.id.rvBoutique)
    RecyclerView rvBoutique;
    @BindView(R.id.srlNewGoods)
    SwipeRefreshLayout srlNewGoods;
    Unbinder unbinder;

    IModelBoutique mModel;
    BoutiqueAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<BoutiqueBean> mArryList;
    public BoutiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_boutique, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initView(layout);
        iniData();
        return layout;
    }

    private void iniData() {
        mModel.downloadBoutiqueList(getContext(), new OnCompletionListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                if(result!=null&&result.length>0){
                    ArrayList<BoutiqueBean> list = ConvertUtils.array2List(result);
                    mAdapter.initData(list);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View layout) {
        mModel = new ModelBoutique();
        mArryList = new ArrayList<>();
        mAdapter = new BoutiqueAdapter(getContext(),mArryList,mModel);
        rvBoutique.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext());
        rvBoutique.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
