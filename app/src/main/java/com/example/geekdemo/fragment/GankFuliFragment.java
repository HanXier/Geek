package com.example.geekdemo.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.SectionAdapter;
import com.example.geekdemo.base.BaseMvpFragment;
import com.example.geekdemo.bean.SectionListBean;
import com.example.geekdemo.mvp.zhihu.SectionPresenter;
import com.example.geekdemo.mvp.zhihu.SectionView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class GankFuliFragment extends BaseMvpFragment <SectionPresenter> implements SectionView {
    private static final String TAG = "SectionFragment";
    @BindView(R.id.view_main)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private SectionAdapter sectionAdapter;

    @Override
    protected SectionPresenter initMvp() {
        return new SectionPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        sectionAdapter = new SectionAdapter(getActivity());

        recyclerView.setAdapter(sectionAdapter);
        //开始下拉
        refreshLayout.setEnableRefresh(true);//启用刷新
        refreshLayout.setEnableLoadmore(true);//启用加载

        //醉了
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
        mPresenter.getSectionList();
    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

    @Override
    public void getSectionListBean(SectionListBean sectionListBean) {


        if (sectionAdapter != null) {
            sectionAdapter.initData(sectionListBean.getData());
        }
    }

    @Override
    public void showErrorMsg(String error) {

        Log.d(TAG, "showErrorMsg: ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && mPresenter != null) {
            mPresenter.getSectionList();

        }
    }
}
