package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;

public class CategoryChildActivity extends AppCompatActivity {

    @BindView(R.id.tv_category_child_pice)
    TextView tvCategoryChildPice;
    @BindView(R.id.tv_category_child_time)
    TextView tvCategoryChildTime;
    @BindView(R.id.fl_category_child)
    FrameLayout flCategoryChild;
    NewGoodsFragment mNewGoodsFragment;
    @BindView(R.id.iv_back_name)
    ImageView ivBackName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        ButterKnife.bind(this);
        mNewGoodsFragment = new NewGoodsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_category_child, mNewGoodsFragment)
                .commit();

    }

    @OnClick({R.id.tv_category_child_pice, R.id.tv_category_child_time,R.id.iv_back_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category_child_pice:
                mNewGoodsFragment.sortGoods(I.SORT_BY_PRICE_ASC);
                break;
            case R.id.tv_category_child_time:
                mNewGoodsFragment.sortGoods(I.SORT_BY_ADDTIME_DESC);
                break;
            case R.id.iv_back_name:
                this.finish();
        }
    }
}
