package com.example.geekdemo.api;

import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;
import com.example.geekdemo.bean.SectionListBean;
import com.example.geekdemo.bean.ZhiHuDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuApis {

    //    http://news-at.zhihu.com/api/4/news/latest


    String zhihuUrl = "http://news-at.zhihu.com/";


    @GET("api/4/news/latest")
    Observable <DailyListBean> getLatestList();

    /**
     * 日报过往数据
     * http://news-at.zhihu.com/api/4/news/before/20190710
     */
    @GET("api/4/news/before/{date}")
    Observable <DailyBeforeBean> getDailyBeforeList(@Path("date") String date);

    /**
     * 专栏日报
     */
    @GET("api/4/sections")
    Observable <SectionListBean> getSectionList();

    /**
     * 日报详情页面
     */
    @GET("api/4/news/{id}")
    Observable <ZhiHuDetailBean> getZhihuDetails(@Path("id") String newsId);

}
