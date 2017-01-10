package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;

public class MainActivity extends AppCompatActivity {
    int index, currentindex;
    RadioButton[] rb;
    @BindView(R.id.rb_NewGoods)
    RadioButton rbNewGoods;
    @BindView(R.id.rb_Boutique)
    RadioButton rbBoutique;
    @BindView(R.id.rb_Category)
    RadioButton rbCategory;
    @BindView(R.id.rb_Cart)
    RadioButton rbCart;
    @BindView(R.id.rb_Personal_center)
    RadioButton rbPersonalCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setRadioStatus();
    }

    private void initView() {

        rb = new RadioButton[5];
        rb[0] = rbNewGoods;
        rb[1] = rbBoutique;
        rb[2] = rbCategory;
        rb[3] = rbCart;
        rb[4] = rbPersonalCenter;
    }

    public void onCheckChanged(View view) {
        switch (view.getId()) {
            case R.id.rb_NewGoods:
                index = 0;
                break;
            case R.id.rb_Boutique:
                index = 0;
                break;
            case R.id.rb_Category:
                index = 0;
                break;
            case R.id.rb_Cart:
                index = 0;
                break;
            case R.id.rb_Personal_center:
                index = 0;
                break;
        }
        if (index != currentindex) {
            setRadioStatus();
        }
        index = currentindex;
    }

    public void setRadioStatus() {
        for (int i = 0; i < rb.length; i++) {
            if (i != index) {
                rb[i].setChecked(false);
            } else {
                rb[i].setChecked(true);
            }
        }
    }
}
