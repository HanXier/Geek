package com.example.geekdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.HotAdapter;
import com.example.geekdemo.api.HotService;
import com.example.geekdemo.bean.HotBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {


    ArrayList <HotBean.RecentBean> list = new ArrayList();
    private View view;
    private RecyclerView mRv;
    private SmartRefreshLayout mRefreshLayout;
    private HotAdapter hotAdapter;

    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(view);
        initretrofit();
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRefreshLayout = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout);
        hotAdapter = new HotAdapter(getActivity(), list);
        mRv.setAdapter(hotAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //开始下拉
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(true);//启用加载


        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initretrofit();
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
                mRefreshLayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Toast.makeText(getActivity(), "没有更多了Ծ‸Ծ", Toast.LENGTH_SHORT).show();
                mRefreshLayout.finishLoadmore();
            }
        });

    }

    private void initretrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HotService.sBaseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        HotService myService = retrofit.create(HotService.class);

        Observable <HotBean> data = myService.getHotData();

        data.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer <HotBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HotBean tab) {
                list.addAll(tab.getRecent());
                hotAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

}
