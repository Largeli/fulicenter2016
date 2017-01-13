package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;

public class CategoryChildActivity extends AppCompatActivity {

    @BindView(R.id.tv_category_child_pice)
    TextView tvCategoryChildPice;
    @BindView(R.id.tv_category_child_time)
    TextView tvCategoryChildTime;
    @BindView(R.id.fl_category_child)
    FrameLayout flCategoryChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_category_child_pice, R.id.tv_category_child_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_category_child_pice:
                break;
            case R.id.tv_category_child_time:
                break;
        }
    }
}
