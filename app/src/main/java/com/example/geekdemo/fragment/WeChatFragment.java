package com.example.geekdemo.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.WeChatAdapter;
import com.example.geekdemo.base.BaseMvpFragment;
import com.example.geekdemo.bean.Newslist;
import com.example.geekdemo.mvp.zhihu.wechat.WeChatPresenter;
import com.example.geekdemo.mvp.zhihu.wechat.WeChatView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class WeChatFragment extends BaseMvpFragment <WeChatPresenter> implements WeChatView {

    private static final String TAG = "WeChatFragment";
    //    view_main
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private WeChatAdapter weChatAdapter;

    @Override
    protected WeChatPresenter initMvp() {
        return new WeChatPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        weChatAdapter = new WeChatAdapter(getActivity());
        recyclerView.setAdapter(weChatAdapter);
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

        mPresenter.getWeChatListData();
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    @Override
    public void updateWeChatList(List <Newslist> weChatList) {
        weChatAdapter.initData(weChatList);
    }

    @Override
    public void showErrorMsg(String error) {

        Log.d(TAG, "showErrorMsg: " + error);
    }


}
