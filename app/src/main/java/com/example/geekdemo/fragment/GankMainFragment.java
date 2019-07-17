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

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.ZhihuMainAdapter;
import com.example.geekdemo.base.BaseFragment;
import com.example.geekdemo.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GankMainFragment extends BaseFragment {
    private static final String TAG = "GankMainFragment";
    public static String[] tabTitle = new String[]{"Android", "iOS", "前端", "福利"};
    @BindView(R.id.tab_zhihu_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_zhihu_main)
    ViewPager mViewPager;
    List <Fragment> fragments = new ArrayList <>();
    BroadcastReceiver gankBroadCastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive: " + intent.getStringExtra("data"));
        }
    };
    private ZhihuMainAdapter mAdapter;

    @Override
    protected void initView() {
        super.initView();

        initBroadcastReceiver();

        // android 页面  传送数据
        GankCommonFragment android = new GankCommonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitle[0]);
        android.setArguments(bundle);

        // ios 页面  传送数据
        GankCommonFragment ios = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitle[1]);
        ios.setArguments(bundle);
        // web 页面  传送数据
        GankCommonFragment web = new GankCommonFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitle[2]);
        web.setArguments(bundle);

        // girl 页面  传送数据
        GankFuliFragment fuli = new GankFuliFragment();
        bundle = new Bundle();
        bundle.putString(Constants.IT_GANK_TYPE, tabTitle[3]);
        fuli.setArguments(bundle);

        fragments.add(android);
        fragments.add(ios);
        fragments.add(web);
        fragments.add(fuli);

        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
    }

    private void initBroadcastReceiver() {

        Log.d(TAG, "initBroadcastReceiver: ");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(gankBroadCastReceiver, new IntentFilter("com.gank.search"));

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "unregisterReceiver: ");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(gankBroadCastReceiver);
    }
}
