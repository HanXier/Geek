package com.example.geekdemo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geekdemo.api.ZhihuApis;
import com.example.geekdemo.base.BaseActivity;
import com.example.geekdemo.base.net.HttpUtils;
import com.example.geekdemo.bean.CollectionDbBean;
import com.example.geekdemo.bean.ZhiHuDetailBean;
import com.example.geekdemo.utils.DbUtils;
import com.example.geekdemo.utils.HtmlUtil;
import com.tencent.smtt.sdk.WebView;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ZhiHuDetailsActivity extends BaseActivity {

    @BindView(R.id.detail_bar_image)
    ImageView detailBarImage;
    @BindView(R.id.detail_bar_copyright)
    TextView detailBarCopyright;
    @BindView(R.id.view_toolbar)
    Toolbar viewToolbar;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.view_main)
    WebView viewMain;
    @BindView(R.id.nsv_scroller)
    NestedScrollView nsvScroller;
    @BindView(R.id.tv_detail_bottom_like)
    TextView tvDetailBottomLike;
    @BindView(R.id.tv_detail_bottom_comment)
    TextView tvDetailBottomComment;
    @BindView(R.id.tv_detail_bottom_share)
    TextView tvDetailBottomShare;
    @BindView(R.id.ll_detail_bottom)
    FrameLayout llDetailBottom;
    @BindView(R.id.fab_like)
    FloatingActionButton fabLike;
    ZhiHuDetailBean zhiHuBean;
    int newsId;

    @Override
    protected void initView() {
        super.initView();
        newsId = getIntent().getIntExtra("id", -1);


        CollectionDbBean dbBean = new CollectionDbBean();
        dbBean.setId(newsId + "");
        CollectionDbBean dbBean1 = DbUtils.queryOne(dbBean);

        if (dbBean1 != null) {
            fabLike.setSelected(true);
        } else {
            fabLike.setSelected(false);
        }

        initData();
    }

    public void initData() {

        //

        HttpUtils.getInstance().buildService(ZhihuApis.zhihuUrl, ZhihuApis.class).getZhihuDetails(newsId + "").compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <ZhiHuDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ZhiHuDetailBean zhiHuDetailBean) {

                zhiHuBean = zhiHuDetailBean;
                Glide.with(ZhiHuDetailsActivity.this).load(zhiHuDetailBean.getImage()).into(detailBarImage);
                clpToolbar.setTitle(zhiHuDetailBean.getTitle());
                detailBarCopyright.setText(zhiHuDetailBean.getImage_source());


                String htmlData = HtmlUtil.createHtmlData(zhiHuDetailBean.getBody(), zhiHuDetailBean.getCss(), zhiHuDetailBean.getJs());

                viewMain.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_zhi_hu_details;
    }

    @Override
    protected void initMvp() {

    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.fab_like)
    public void onClick() {

        CollectionDbBean dbBean = new CollectionDbBean();

        dbBean.setFromType(1);
        dbBean.setId(newsId + "");
        dbBean.setImgUrl(zhiHuBean.getImage());
        dbBean.setTitle(zhiHuBean.getTitle());

        DbUtils.insertBean(dbBean);


    }
}
