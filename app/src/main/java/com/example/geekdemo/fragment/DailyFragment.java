package com.example.geekdemo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.geekdemo.CalenderActivity;
import com.example.geekdemo.R;
import com.example.geekdemo.ZhiHuDetailsActivity;
import com.example.geekdemo.adapter.DailyAdapter;
import com.example.geekdemo.base.BaseMvpFragment;
import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;
import com.example.geekdemo.mvp.zhihu.DailyPresenter;
import com.example.geekdemo.mvp.zhihu.DailyView;
import com.example.geekdemo.utils.DateUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DailyFragment extends BaseMvpFragment <DailyPresenter> implements DailyView {
    private static final String TAG = "DailyFragment";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.fab_calender)
    FloatingActionButton floatingActionButton;

    boolean isBefore;
    private DailyAdapter dailyAdapter;
    BroadcastReceiver myBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String date = intent.getStringExtra("date");


            // TODO 是否是当天
            String yyyymmdd = DateUtils.getYYYYMMDD();
            if (date.equals(yyyymmdd)) {
                isBefore = false;
                dailyAdapter.setIsBefore(isBefore, "今日新闻");

                mPresenter.getCurrListData();
            } else {
                isBefore = true;
                dailyAdapter.setIsBefore(isBefore, date);
                mPresenter.getDailyBeforeData(date);
            }


            Log.d(TAG, "onReceive: 日期为=" + date + "---" + isBefore);
        }
    };

    @Override
    protected void initView() {
        super.initView();

        initBroadcastManager();
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        dailyAdapter = new DailyAdapter(getActivity());

        recyclerview.setAdapter(dailyAdapter);

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
        mPresenter.getCurrListData();

        dailyAdapter.setOnItemClick(new DailyAdapter.OnItemClick() {
            @Override
            public void itemClick(int newsId) {
                Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                intent.putExtra("id", newsId);
                startActivity(intent);
            }
        });

    }

    public void initBroadcastManager() {

        IntentFilter intentFilter = new IntentFilter("com.geekdemo.date");

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(myBroadCastReceiver, intentFilter);

    }

    public void unRegisterBroadcast() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(myBroadCastReceiver);


    }

    @OnClick(R.id.fab_calender)
    public void fabOnClick(View view) {
        startActivity(new Intent(getActivity(), CalenderActivity.class));
    }

    @Override
    protected DailyPresenter initMvp() {
        return new DailyPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.daily_fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unRegisterBroadcast();
            unbinder.unbind();
        }

    }

    @Override
    public void getCurrData(DailyListBean dailyListBean) {

        dailyAdapter.initBanner(dailyListBean.getTop_stories());
        dailyAdapter.refreshDate(dailyListBean.getDate());
        dailyAdapter.initDailyList(dailyListBean.getStories());

    }

    @Override
    public void getBeforeData(DailyBeforeBean dailyBeforeBean) {

        dailyAdapter.initBeforeData(dailyBeforeBean.getStories());
    }

    @Override
    public void showErrorMsg(String error) {

        Log.d(TAG, "showErrorMsg: ");
    }


}
