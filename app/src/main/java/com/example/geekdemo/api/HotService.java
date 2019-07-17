package com.example.geekdemo.api;


import com.example.geekdemo.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotService {

    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    /**
     * 热门
     *
     * @return
     */
    @GET("news/hot")
    Observable <HotBean> getHotData();

}
