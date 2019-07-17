package com.example.geekdemo.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.geekdemo.R;
import com.example.geekdemo.adapter.GankAdapter;
import com.example.geekdemo.base.BaseMvpFragment;
import com.example.geekdemo.bean.GankBean;
import com.example.geekdemo.mvp.gank.GankPresenter;
import com.example.geekdemo.mvp.gank.GankView;
import com.example.geekdemo.utils.Constants;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.Unbinder;

public class GankCommonFragment extends BaseMvpFragment <GankPresenter> implements GankView {


    private static final String TAG = "GankCommonFragment";
    @BindView(R.id.iv_tech_blur)
    ImageView ivTechBlur;
    @BindView(R.id.iv_tech_origin)
    ImageView ivTechOrigin;
    @BindView(R.id.tv_tech_copyright)
    TextView tvTechCopyright;
    @BindView(R.id.tech_appbar)
    AppBarLayout techAppbar;
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private String key;
    private GankAdapter gankAdapter;

    @Override
    protected GankPresenter initMvp() {
        return new GankPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        Bundle arguments = getArguments();
        key = arguments.getString(Constants.IT_GANK_TYPE);

        String imgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563884195&di=f71d358c934edf9fd8cd0af28f26a41f&imgtype=jpg&er=1&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201504%2F24%2F20150424H1957_FjZWx.jpeg";

        Glide.with(getActivity()).load(imgUrl).into(ivTechBlur);


        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        gankAdapter = new GankAdapter(getActivity());

        viewMain.setAdapter(gankAdapter);


        //开始下拉
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "没有更多了Ծ‸Ծ", Toast.LENGTH_SHORT).show();
                refreshLayout.finishLoadmore();
            }
        });
        // 调用接口请求
        mPresenter.getGankList(key);
    }

    @Override
    protected int getLayout() {
        return R.layout.gank__fragment;
    }


    @Override
    public void showErrorMsg(String error) {

        Log.d(TAG, "showErrorMsg: ");
    }


    @Override
    public void getGankListData(GankBean gankBean) {

        if (gankAdapter != null) {
            gankAdapter.initData(gankBean.getResults());
        }

    }


}
