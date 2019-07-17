package com.example.geekdemo.base.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {


    private static Retrofit.Builder retrofitBuilder;


    private static HttpUtils httpUtils;

    private HttpUtils() {


        if (retrofitBuilder == null) {
            retrofitBuilder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
    }


    public static synchronized HttpUtils getInstance() {


        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }


    public static <T> T buildService(String url, Class <T> tClass) {
        return retrofitBuilder.baseUrl(url).build().create(tClass);

    }


    /**
     * 调度线程
     *
     * @return
     */
    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
