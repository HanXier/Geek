package com.example.geekdemo.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.TopicAdapter;
import com.example.geekdemo.base.BaseFragment;
import com.example.geekdemo.bean.TopicListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VtexCommonFragment extends BaseFragment {


    @BindView(R.id.view_main)
    RecyclerView viewMain;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    String type = "";
    String typeName = "";
    private TopicAdapter topicAdapter;
    private List <TopicListBean> topicListBeanList;

    @Override
    protected void initView() {
        super.initView();

        typeName = getArguments().getString("typeName");
        type = getArguments().getString("type");


        viewMain.setLayoutManager(new LinearLayoutManager(getActivity()));

        topicAdapter = new TopicAdapter(getActivity());

        viewMain.setAdapter(topicAdapter);
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

        initData(type);
    }

    public void initData(final String type) {


        new Thread() {
            @Override
            public void run() {
                super.run();


                try {
                    Document doc = Jsoup.connect("https://www.v2ex.com/?tab=" + type).timeout(10000).get();

                    Elements itemElements = doc.select("div.cell.item");

                    topicListBeanList = new ArrayList <>();
                    for (int x = 0; x < itemElements.size(); x++) {


                        Elements titleEle = itemElements.get(x).select("div.cell.item table tr td span.item_title > a");
                        Elements imgEle = itemElements.get(x).select("div.cell.item table tr td img.avatar");
                        Elements commentEle = itemElements.get(x).select("div.cell.item table tr a.count_livid");

                        TopicListBean topicListBean = new TopicListBean();

                        if (titleEle.size() > 0) {
                            topicListBean.setTitle(titleEle.get(0).text());
                        }
                        if (imgEle.size() > 0) {

                            topicListBean.setImgUrl("http:" + imgEle.get(0).attr("src"));
                        }

                        if (commentEle.size() > 0) {
                            String topicId = commentEle.get(0).attr("href");

                            String text = commentEle.get(0).text();

                            int i = topicId.indexOf("#");

                            topicId = topicId.substring(3, i);


                            topicListBean.setCommentNum(Integer.valueOf(topicId));
                        }

                        topicListBeanList.add(topicListBean);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        topicAdapter.initData(topicListBeanList);
                    }
                });

            }
        }.start();


    }


    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.view_common_list;
    }

}
