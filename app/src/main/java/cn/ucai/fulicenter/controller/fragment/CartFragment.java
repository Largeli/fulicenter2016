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
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.adapter.BoutiqueAdapter;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModelUser;
import cn.ucai.fulicenter.model.net.ModeUser;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.ConvertUtils;
import cn.ucai.fulicenter.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    @BindView(R.id.srlBoutique)
    SwipeRefreshLayout srlBoutique;
    @BindView(R.id.tv_nothing)
    TextView tvNomore;
    @BindView(R.id.tvRefreshHint)
    TextView tvRefreshHint;
    @BindView(R.id.rvBoutique)
    RecyclerView rvBoutique;

    Unbinder unbinder;

    IModelUser mModel;
    BoutiqueAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<BoutiqueBean> mArryList;
    User user ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, layout);
        // Log.e("main", "lay");
        initView(layout);
        // L.e("main", "initView");
        user = FuLiCenterApplication.getUser();
        iniData(I.ACTION_DOWNLOAD);
        // L.e("main", "inidatafinsh");
        setListener();
        return layout;
    }

    private void setListener() {
        srlBoutique.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlBoutique.setRefreshing(true);
                tvRefreshHint.setVisibility(View.VISIBLE);
                iniData(I.ACTION_PULL_DOWN);
            }
        });
    }

    private void iniData(final int action) {
        if (user == null) {
            MFGT.gotoLogin(getActivity());
        }else {
            mModel.getCart(getContext(), user.getMuserName(),
                    new OnCompletionListener<CartBean[]>() {
                        @Override
                        public void onSuccess(CartBean[] result) {
                            srlBoutique.setRefreshing(false);
                            tvRefreshHint.setVisibility(View.GONE);
                            //  L.e("main", result.length + "");
                            if (result != null && result.length > 0) {
                                ArrayList<CartBean> list = ConvertUtils.array2List(result);
                                if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                                  //  mAdapter.initData(list);
                                } else {
                                   // mAdapter.addData(list);
                                }
                            } else {
                                rvBoutique.setVisibility(View.GONE);
                                tvNomore.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onError(String error) {
                            // Log.e("main", "gg");
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void initView(View layout) {
        mModel = new ModeUser();
        mArryList = new ArrayList<>();
       // mAdapter = new BoutiqueAdapter(getContext(), mArryList, mModel);
        mLayoutManager = new LinearLayoutManager(getContext());
        rvBoutique.setLayoutManager(mLayoutManager);
        rvBoutique.setAdapter(mAdapter);
    }
    @OnClick(R.id.tv_nothing)
    public void onClick() {
        // L.e("main", "onclick");
        iniData(I.ACTION_DOWNLOAD);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
