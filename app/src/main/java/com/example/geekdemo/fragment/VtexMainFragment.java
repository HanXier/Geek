package com.example.geekdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.geekdemo.NodeActivity;
import com.example.geekdemo.R;
import com.example.geekdemo.adapter.ZhihuMainAdapter;
import com.example.geekdemo.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VtexMainFragment extends BaseFragment {


    public static String[] typeStr = {"技术", "创意", "好玩", "Apple", "酷工作", "交易", "城市", "问与答", "最热", "全部", "R2"};
    public static String[] type = {"tech", "creative", "play", "apple", "jobs", "deals", "city", "qna", "hot", "all", "r2"};
    List <Fragment> fragments = new ArrayList <>();
    @BindView(R.id.tab_gold_main)
    TabLayout tabGoldMain;
    @BindView(R.id.iv_gold_menu)
    ImageView ivGoldMenu;
    @BindView(R.id.vp_gold_main)
    ViewPager vpGoldMain;
    Unbinder unbinder;
    private ZhihuMainAdapter mAdapter;

    @Override
    protected void initView() {
        super.initView();


        tabGoldMain.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabGoldMain.setupWithViewPager(vpGoldMain);

        updateTabData();
    }

    /**
     * 第一步：tablayou  tab
     * 第二步：fragments  viewpgeger add
     */
    public void updateTabData() {

        fragments.clear();
        tabGoldMain.removeAllTabs();

        for (int x = 0; x < typeStr.length; x++) {

            VtexCommonFragment vtexCommonFragment = new VtexCommonFragment();

            Bundle bundle = new Bundle();

            bundle.putString("typeName", typeStr[x]);
            bundle.putString("type", type[x]);

            vtexCommonFragment.setArguments(bundle);

            tabGoldMain.addTab(tabGoldMain.newTab().setText(typeStr[x]));
            fragments.add(vtexCommonFragment);

        }
        mAdapter = new ZhihuMainAdapter(getChildFragmentManager(), fragments);
        vpGoldMain.setAdapter(mAdapter);

        tabGoldMain.setupWithViewPager(vpGoldMain);
        for (int x = 0; x < typeStr.length; x++) {  // 遍历源数据
            tabGoldMain.getTabAt(x).setText(typeStr[x]);
        }
    }


    @Override
    protected void initPresenter() {

    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_gold_main;
    }

    @OnClick(R.id.iv_gold_menu)
    public void onClick() {

        Intent intent = new Intent(getActivity(), NodeActivity.class);
        startActivity(intent);
    }

}
