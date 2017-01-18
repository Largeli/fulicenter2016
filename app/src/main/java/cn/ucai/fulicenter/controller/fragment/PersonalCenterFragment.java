package cn.ucai.fulicenter.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.FuLiCenterApplication;
import cn.ucai.fulicenter.model.bean.User;
import cn.ucai.fulicenter.model.utils.ImageLoader;
import cn.ucai.fulicenter.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalCenterFragment extends Fragment {


    @BindView(R.id.iv_user_Avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    Unbinder unbinder;

    public PersonalCenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_personal_center, container, false);
        unbinder = ButterKnife.bind(this, layout);
        inintData();
        return layout;
    }

    private void inintData() {
        User user = FuLiCenterApplication.getUser();
        if (user != null) {
            loadUserInfo(user);
        } else {
            //MFGT.gotoLogin(getActivity());
        }
    }

    private void loadUserInfo(User user) {
       // ImageLoader.downloadImg(getContext(), mIvUserAvatar, user.getAvatarPath());
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user),getContext(),mIvUserAvatar);
        mTvUserName.setText(user.getMuserNick());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_user_Avatar, R.id.tv_user_name, R.id.tv_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_Avatar:
            case R.id.tv_user_name:
            case R.id.tv_set:
                if (FuLiCenterApplication.getUser()!=null){
                    MFGT.gotoSettings(getActivity());
                }else{
                    MFGT.gotoLogin(getActivity());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        inintData();
    }
}
