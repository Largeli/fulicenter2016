package cn.ucai.fulicenter.controller.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.controller.fragment.CartFragment;
import cn.ucai.fulicenter.controller.fragment.CategoryFragment;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.controller.fragment.PersonalCenterFragment;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.view.MFGT;

public class MainActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    Context mContext;
    @BindView(R.id.rg_footer)
    RadioGroup rgFooter;
    @BindView(R.id.fragment_vp)
    ViewPager fragmentVp;
    int index, currentindex;
    List<Fragment> mFragment;
    NewGoodsFragment mNewGoodsFramgent;
    BoutiqueFragment mBoutiqueFragment;
    CartFragment mCartFragment;
    CategoryFragment mCategoryFragment;
    PersonalCenterFragment mPersonalCenterFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        //setRadioStatus();

    }

    private void initView() {
        mBoutiqueFragment = new BoutiqueFragment();
        mCartFragment = new CartFragment();
        mCategoryFragment = new CategoryFragment();
        mNewGoodsFramgent = new NewGoodsFragment();
        mPersonalCenterFragment = new PersonalCenterFragment();
        mFragment = new ArrayList<Fragment>();
        mFragment.add(mNewGoodsFramgent);
        mFragment.add(mBoutiqueFragment);
        mFragment.add(mCategoryFragment);
        mFragment.add(mCartFragment);
        mFragment.add(mPersonalCenterFragment);
        rgFooter.setOnCheckedChangeListener(this);
        fragmentVp.setOffscreenPageLimit(5);
        fragmentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        });
        fragmentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) rgFooter.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentVp.setCurrentItem(0);
        ((RadioButton)rgFooter.getChildAt(index)).setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_NewGoods:
                index = 0;
                break;
            case  R.id.rb_Boutique:
                index = 1;
                //L.e("main","Botique");
                break;
            case R.id.rb_Category:
                index = 2;
                break;
            case  R.id.rb_Cart:
                index = 3;
                break;
            case  R.id.rb_Personal_center:
                if (FuLiCenterApplication.getUser() == null) {
                   MFGT.gotoLogin(this);
                }
                index = 4;
                break;
        }
        if (fragmentVp.getCurrentItem() == index) {
            return;
        }
        fragmentVp.setCurrentItem(index);
    }
    @Override
    public void onResume() {
        super.onResume();
        L.e(TAG,"onResume,currentIndex="+currentindex+",index="+index
        +",user="+FuLiCenterApplication.getUser());
       fragmentVp.setCurrentItem(index);
    }


}
