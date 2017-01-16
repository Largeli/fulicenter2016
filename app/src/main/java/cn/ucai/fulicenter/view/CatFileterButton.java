package cn.ucai.fulicenter.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.activity.CategoryChildActivity;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.model.utils.ImageLoader;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class CatFileterButton extends Button {
    Context mcontext;
    boolean isExpand ;
    PopupWindow mPopupWindow;
    CatFileterAdapter adapter;
    GridView mGridView;
    String groupName;
    public CatFileterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;


    }

    public void initCatFileterButton(String groupName, ArrayList<CategoryChildBean> list) {
        this.groupName = groupName;
        this.setText(groupName);
        setCatFileterListener();
        adapter = new CatFileterAdapter(mcontext,list);
        initGridView();
    }

    private void initGridView() {
        mGridView = new GridView(mcontext);
        mGridView.setVerticalSpacing(10);
        mGridView.setHorizontalSpacing(10);
        mGridView.setNumColumns(GridView.AUTO_FIT);
        mGridView.setAdapter(adapter);
    }

    private void setCatFileterListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpand) {
                    initpopup();
                } else {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
                setArrow();
            }
        });
    }

    private void initpopup() {
        mPopupWindow = new PopupWindow();
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xbb000000));

        mPopupWindow.setContentView(mGridView);
        mPopupWindow.showAsDropDown(this);
    }

    private void setArrow() {
        Drawable right;
        if (!isExpand) {
            right = getResources().getDrawable(R.drawable.arrow2_up);
        } else {
            right = getResources().getDrawable(R.drawable.arrow2_down);
        }
        right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
        isExpand = !isExpand;
    }

    class CatFileterAdapter extends BaseAdapter {
        Context context;
        ArrayList<CategoryChildBean> list;

        public CatFileterAdapter(Context context, ArrayList<CategoryChildBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            CatFileterViewHolder vh = null;
            if (view == null) {
                view = LayoutInflater.from(mcontext).inflate( R.layout.item_category_popup, null);
                vh = new CatFileterViewHolder(view);
                view.setTag(vh);
            } else {
                vh = (CatFileterViewHolder)view.getTag();

            }
            vh.bind(i);
            return view;
        }


    class CatFileterViewHolder {
        @BindView(R.id.iv_popup_Avatar)
        ImageView ivPopupAvatar;
        @BindView(R.id.tv_popup_title)
        TextView tvPopupTitle;

        CatFileterViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
        public void bind(final int position){
            ImageLoader.downloadImg(context, ivPopupAvatar, list.get(position).getImageUrl());
            tvPopupTitle.setText(list.get(position).getName());
            tvPopupTitle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    MFGT.gotoCategoryChild(context,
                            groupName,
                            list.get(position).getId(),
                            list
                    );
                    MFGT.finish((CategoryChildActivity) mcontext);
                }
            });
        }
    }
    }
}
