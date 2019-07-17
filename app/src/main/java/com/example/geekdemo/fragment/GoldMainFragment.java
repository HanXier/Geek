package com.example.geekdemo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geekdemo.GoldManagerActivity;
import com.example.geekdemo.R;
import com.example.geekdemo.adapter.ZhihuMainAdapter;
import com.example.geekdemo.base.BaseFragment;
import com.example.geekdemo.utils.Constants;
import com.example.geekdemo.utils.GoldStatus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GoldMainFragment extends BaseFragment {


    private static final String TAG = "GoldMainFragment";
    List <Fragment> fragments = new ArrayList <>();
    @BindView(R.id.tab_gold_main)
    TabLayout tabGoldMain;
    @BindView(R.id.iv_gold_menu)
    ImageView ivGoldMenu;
    @BindView(R.id.vp_gold_main)
    ViewPager vpGoldMain;
    Unbinder unbinder;
    private ZhihuMainAdapter mAdapter;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive: 接收到刷新");
            // TODO  刷新 tablayout 数据
            updateTabData();

        }
    };

    @Override
    protected void initView() {
        super.initView();

        IntentFilter intentFilter = new IntentFilter("update.gold");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, intentFilter);

        tabGoldMain.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabGoldMain.setupWithViewPager(vpGoldMain);

        updateTabData();
    }

    public void updateTabData() {

        fragments.clear();
        tabGoldMain.removeAllTabs();

        for (int x = 0; x < Constants.goldStatuses.size(); x++) {
            GoldStatus goldStatus = Constants.goldStatuses.get(x);
            if (goldStatus.isSelected()) {
                GoldCommonFragment common = new GoldCommonFragment();

                tabGoldMain.addTab(tabGoldMain.newTab().setText(goldStatus.getTitle()).setTag(x));
                fragments.add(common);
            }
        }
        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments);
        vpGoldMain.setAdapter(mAdapter);

        tabGoldMain.setupWithViewPager(vpGoldMain);

        int currIndex = 0;

        for (int x = 0; x < Constants.goldStatuses.size(); x++) {  // 遍历源数据
            GoldStatus goldStatus = Constants.goldStatuses.get(x);
            if (goldStatus.isSelected()) {  //判断源数据 选中
                String title = Constants.goldStatuses.get(x).getTitle();

                TabLayout.Tab tabAt = tabGoldMain.getTabAt(currIndex++);
                tabAt.setText(title);
            }
        }

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold_main;
    }

    @OnClick(R.id.iv_gold_menu)
    public void onClick() {

        Intent intent = new Intent(getActivity(), GoldManagerActivity.class);
        startActivity(intent);
    }
}
