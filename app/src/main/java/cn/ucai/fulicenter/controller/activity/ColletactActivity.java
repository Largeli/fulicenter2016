package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.CollectBean;
import cn.ucai.fulicenter.model.net.IModelUser;
import cn.ucai.fulicenter.model.net.ModeUser;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.ConvertUtils;
import cn.ucai.fulicenter.model.utils.L;

public class ColletactActivity extends AppCompatActivity {
    private static final String TAG = ColletactActivity.class.getSimpleName();
    IModelUser model;
    int pageId = 1;
    @BindView(R.id.rlv_collect)
    RecyclerView rlvCollect;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colletact);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        model = new ModeUser();
        model.getcollect(this, FuLiCenterApplication.getUser().getMuserName(), pageId, I.PAGE_SIZE_DEFAULT, new OnCompletionListener<CollectBean[]>() {
            @Override
            public void onSuccess(CollectBean[] result) {
                if (result == null) {

                } else {
                    ArrayList<CollectBean> list = ConvertUtils.array2List(result);
                    L.e(TAG, "list=" + list.size());
                }
            }

            @Override
            public void onError(String error) {
                L.e(TAG, "err0r=" + error);
            }
        });
    }

    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_red),
                getResources().getColor(R.color.google_yellow)

        );
    }

    @OnClick(R.id.iv_collect_back)
    public void onClick() {
        this.finish();
    }
}
