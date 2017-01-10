package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import cn.ucai.fulicenter.R;

public class MainActivity extends AppCompatActivity {
    RadioButton rb_NewGoods,rb_Boutique,rb_Category,rb_Cart,rb_Personal_center;
    int index ,currentindex;
    RadioButton[] rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setRadioStatus();
    }

    private void initView() {
        rb_NewGoods = (RadioButton) findViewById(R.id.rb_NewGoods);
        rb_Boutique = (RadioButton) findViewById(R.id.rb_Boutique);
        rb_Category = (RadioButton) findViewById(R.id.rb_Category);
        rb_Cart = (RadioButton) findViewById(R.id.rb_Cart);
        rb_Personal_center = (RadioButton) findViewById(R.id.rb_Personal_center);

        rb = new RadioButton[5];
        rb[0] = rb_NewGoods;
        rb[1] = rb_Boutique;
        rb[2] = rb_Category;
        rb[3] = rb_Cart;
        rb[4] = rb_Personal_center;
    }
    public void onCheckChanged(View view){
        switch (view.getId()){
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
        index=currentindex;
    }
    public void setRadioStatus(){
        for(int i=0; i<rb.length;i++){
            if (i != index) {
                rb[i].setChecked(false);
            }else {
                rb[i].setChecked(true);
            }
        }
    }
}
