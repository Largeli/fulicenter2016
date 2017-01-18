package cn.ucai.fulicenter.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.net.SharedPrefrenceUtils;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.view.MFGT;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = SettingActivity.class.getSimpleName();
    @BindView(R.id.iv_personinfo_avatar)
    ImageView mIvPersoninfoAvatar;
    @BindView(R.id.tv_personinfo_username)
    TextView mTvPersoninfoUsername;
    @BindView(R.id.tv_personinfo_nick)
    TextView mTvPersoninfoNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        inintData();
    }

    private void inintData() {
        User user = FuLiCenterApplication.getUser();
        if (user != null) {
            loadUserInfo(user);
        } else {
            MFGT.gotoLogin(this);
        }
    }

    private void loadUserInfo(User user) {
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), this, mIvPersoninfoAvatar);
        mTvPersoninfoUsername.setText(user.getMuserName());
        mTvPersoninfoNick.setText(user.getMuserNick());
    }
    @OnClick(R.id.iv_personinfo_back)
    public void back() {
        finish();
    }
    @OnClick(R.id.btn_personinfo_exit)
    public void onClick() {
        FuLiCenterApplication.setUser(null);
        SharedPrefrenceUtils.getInstance(this).removeUser();
        MFGT.gotoLogin(this);
        finish();
    }

    @OnClick(R.id.rl_nick)
    public void updateNick() {
        String nick = mTvPersoninfoNick.getText().toString().trim();
        //L.e(TAG,"nick="+nick);
        if (!TextUtils.isEmpty(nick)) {
            MFGT.gotoUpdata(this, nick);
            // L.e(TAG,"updateNick");
        }
        //L.e(TAG,"updateNick22");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.e(TAG,"request="+requestCode+",resultCode="+resultCode);
        if (resultCode == RESULT_OK && requestCode == I.REQUEST_CODE_NICK) {
            mTvPersoninfoNick.setText(FuLiCenterApplication.getUser().getMuserNick());
        }
    }
}
