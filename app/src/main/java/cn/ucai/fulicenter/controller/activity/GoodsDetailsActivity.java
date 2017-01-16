package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.controller.application.I;
import cn.ucai.fulicenter.model.bean.AlbumsBean;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.net.IModelGoodes;
import cn.ucai.fulicenter.model.net.ModelGoodes;
import cn.ucai.fulicenter.model.net.OnCompletionListener;
import cn.ucai.fulicenter.model.utils.L;
import cn.ucai.fulicenter.view.FlowIndicator;
import cn.ucai.fulicenter.view.MFGT;
import cn.ucai.fulicenter.view.SlideAutoLoopView;

public class GoodsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.tv_GoodsEnlishName)
    TextView tvGoodsEnlishName;
    @BindView(R.id.tv_GoodName)
    TextView tvGoodName;
    @BindView(R.id.tv_GoodsPich)
    TextView tvGoodsPich;
    @BindView(R.id.salv)
    SlideAutoLoopView salv;
    @BindView(R.id.indicator)
    FlowIndicator indicator;

    IModelGoodes mModle;
    int goodId;
    @BindView(R.id.wv)
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        goodId = getIntent().getIntExtra(I.GoodsDetails.KEY_GOODS_ID, I.CAT_ID);
        L.e("main","mgoodid"+goodId);
        if (goodId == I.CAT_ID) {
            L.e("main","000");
            MFGT.finish(this);
        } else {
            initData();
        }
    }

    private void initData() {
        mModle = new ModelGoodes();
        mModle.downloadList(this, goodId, new OnCompletionListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean result) {
                if (result != null) {
                    showGoodDetails(result);
                }
            }

            @Override
            public void onError(String error) {

            }
        });

    }

    private void showGoodDetails(GoodsDetailsBean result) {
        tvGoodName.setText(result.getGoodsName());
        tvGoodsEnlishName.setText(result.getGoodsEnglishName());
        tvGoodsPich.setText(result.getCurrencyPrice());
        salv.startPlayLoop(indicator,getAlbumUrl(result),getAlbumCount(result));
        wv.loadDataWithBaseURL(null,result.getGoodsBrief(),"text/txt",I.UTF_8,null);
    }

    private int getAlbumCount(GoodsDetailsBean goods) {
        if (goods != null && goods.getProperties() != null && goods.getProperties().length > 0) {
            return goods.getProperties()[0].getAlbums().length;

        }
        return 0;
    }

    private String[] getAlbumUrl(GoodsDetailsBean goods) {
        if (goods != null && goods.getProperties() != null && goods.getProperties().length > 0) {
            AlbumsBean[] albums = goods.getProperties()[0].getAlbums();
            if (albums != null && albums.length > 0) {
                String[] urls = new String[albums.length];
                for(int i=0;i<albums.length;i++) {
                    urls[i] = albums[i].getImgUrl();
                }
                return urls;
            }
        }
        return new String[0];
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.iv_collect, R.id.iv_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                MFGT.finish(this);
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_collect:
                break;
            case R.id.iv_cart:
                break;
        }
    }
}