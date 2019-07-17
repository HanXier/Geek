package com.example.geekdemo.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.geekdemo.R;
import com.example.geekdemo.ZhiHuDetailsActivity;
import com.example.geekdemo.adapter.CollectionListAdapter;
import com.example.geekdemo.base.BaseFragment;
import com.example.geekdemo.bean.CollectionDbBean;
import com.example.geekdemo.utils.DbUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class CollectionFragment extends BaseFragment {
    private static final String TAG = "CollectionFragment";
    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected void initView() {
        super.initView();

        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        CollectionListAdapter collectionListAdapter = new CollectionListAdapter(getActivity());
        viewMain.setAdapter(collectionListAdapter);

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
        List <CollectionDbBean> collectionDbBeans = DbUtils.queryAll();

        collectionListAdapter.initData(collectionDbBeans);
        collectionListAdapter.setItemClickListener(new CollectionListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int type, String id) {

                Log.d(TAG, "onItemClick: " + type + "---id=" + id);

                if (type == 1) {
                    Intent intent = new Intent(getActivity(), ZhiHuDetailsActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                } else if (type == 2) {

                }
            }
        });
    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

}
