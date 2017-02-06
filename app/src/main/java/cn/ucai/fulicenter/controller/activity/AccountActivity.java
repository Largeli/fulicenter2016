package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.I;

public class AccountActivity extends AppCompatActivity {

    @BindView(R.id.et_account_name)
    EditText etAccountName;
    @BindView(R.id.et_account_phone)
    EditText etAccountPhone;
    @BindView(R.id.sp_account)
    Spinner spAccount;
    @BindView(R.id.et_center)
    EditText etCenter;
    @BindView(R.id.tv_account_price)
    TextView mTvOrderPrice;
    int payPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        payPrice = getIntent().getIntExtra(I.Cart.PAY_PRICE,0);
        setView();
    }

    private void setView() {
        mTvOrderPrice.setText("合计：￥"+payPrice);
    }

    @OnClick({R.id.back, R.id.btn_account})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_account:

                break;
        }
    }
}
