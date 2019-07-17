package com.example.geekdemo.api;

import com.example.geekdemo.bean.Newslist;
import com.example.geekdemo.bean.WechatResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeChatApis {

    String wechatUrl = "http://api.tianapi.com/";
//    ?key=52b7ec3471ac3bec6846577e79f20e4c&num=20&page=1

    @GET("wxnew")
    Observable <WechatResult <List <Newslist>>> getWechatList(@Query("key") String key, @Query("num") String num, @Query("page") String page);
}
