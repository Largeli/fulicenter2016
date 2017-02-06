package cn.ucai.fulicenter.controller.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import cn.ucai.fulicenter.controller.adapter.CartAdapter;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.IModelUser;
import cn.ucai.fulicenter.model.net.ModeUser;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.CommonUtils;
import cn.ucai.fulicenter.model.utils.ConvertUtils;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private static final String TAG = CartFragment.class.getSimpleName();
    @BindView(R.id.srl)
    SwipeRefreshLayout msrl;
    @BindView(R.id.tv_nothing)
    TextView tvnothing;
    @BindView(R.id.tv_RefreshHint)
    TextView tvRefreshHint;
    @BindView(R.id.rv)
    RecyclerView mRv;

    Unbinder unbinder;

    IModelUser mModel;
    CartAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    ArrayList<CartBean> mArryList;
    User user;
    @BindView(R.id.tv_cart_sum_price)
    TextView tvCartSumPrice;
    @BindView(R.id.tv_cart_save_price)
    TextView tvCartSavePrice;
    UpdateCartReceiver mReceiver;
    int sumPrice = 0;
    int payPrice  = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initView(layout);
        user = FuLiCenterApplication.getUser();
        iniData(I.ACTION_DOWNLOAD);
        setListener();
        setReceiverListener();
        return layout;
    }

    private void setReceiverListener() {
        mReceiver = new UpdateCartReceiver();
        IntentFilter filter = new IntentFilter(I.BROADCAST_UPDATA_CART);
        getContext().registerReceiver(mReceiver, filter);
    }

    private void setListener() {
        msrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                msrl.setRefreshing(true);
                tvRefreshHint.setVisibility(View.VISIBLE);
                iniData(I.ACTION_PULL_DOWN);
            }
        });
    }

    private void iniData(final int action) {
        if (user == null) {
            MFGT.gotoLogin(getActivity());
        } else {
            mModel.getCart(getContext(), user.getMuserName(),
                    new OnCompletionListener<CartBean[]>() {
                        @Override
                        public void onSuccess(CartBean[] result) {
                            msrl.setRefreshing(false);
                            tvRefreshHint.setVisibility(View.GONE);
                            //  L.e("main", result.length + "");
                            if (result != null && result.length > 0) {
                                ArrayList<CartBean> list = ConvertUtils.array2List(result);
                                mArryList.addAll(list);
                                if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                                    mAdapter.initData(list);
                                } else {
                                    mAdapter.addData(list);
                                }
                            } else {
                                mRv.setVisibility(View.GONE);
                                tvnothing.setVisibility(View.VISIBLE);
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
        mAdapter = new CartAdapter(getContext(), mArryList);
        L.e(TAG, "mAdapter,mArrayList=" + mArryList.toString());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRv.setHasFixedSize(true);
        msrl.setVisibility(View.VISIBLE);
        tvnothing.setVisibility(View.GONE);
        mRv.setLayoutManager(mLayoutManager);
        mRv.setAdapter(mAdapter);
    }

    @OnClick(R.id.tv_nothing)
    public void onClick() {
        // L.e("main", "onclick");
        iniData(I.ACTION_DOWNLOAD);
    }

    @Override
    public void onResume() {
        super.onResume();
        setPrice();
        L.e("main", "setPrice");
    }

    private void setPrice() {
         sumPrice = 0;
        payPrice = 0;
        int savePrice = 0;
        if (mArryList != null && mArryList.size() > 0) {
            for (CartBean cart : mArryList) {
                GoodsDetailsBean goods = cart.getGoods();
                if (cart.isChecked() && goods != null) {
                    sumPrice += cart.getCount() * getPrice(goods.getCurrencyPrice());
                    savePrice += cart.getCount() * (getPrice(goods.getCurrencyPrice()) - getPrice(goods.getRankPrice()));
                }
            }
        }
        tvCartSumPrice.setText("合计:￥" + sumPrice);
       // L.e(TAG, "sumPrice=" + sumPrice);
        tvCartSavePrice.setText("节省:￥" + savePrice);
       // L.e(TAG, "savePrice=" + savePrice);
        mAdapter.notifyDataSetChanged();
        payPrice = sumPrice-savePrice;
    }

    int getPrice(String price) {
        int p = 0;
        p = Integer.valueOf(price.substring(price.indexOf("￥") + 1));
        return p;
    }

    @OnClick(R.id.tv_cart_buy)
    public void onBuClick() {
        if (sumPrice > 0) {
           L.e(TAG,"sumPrice="+sumPrice);
            MFGT.gotoAccount(getActivity(),payPrice);
        }else {
            CommonUtils.showLongToast(R.string.order_nothing);
        }
    }

    class UpdateCartReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            L.e(TAG, "onReceive");
            setPrice();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mReceiver != null) {
            getContext().unregisterReceiver(mReceiver);
        }
    }
}
