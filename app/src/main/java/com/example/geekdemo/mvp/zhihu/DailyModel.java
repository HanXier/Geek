package com.example.geekdemo.mvp.zhihu;

import android.util.Log;

import com.example.geekdemo.api.ZhihuApis;
import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.base.net.HttpUtils;
import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class DailyModel extends BaseModel {


    private static final String TAG = "DailyModel";

    /**
     * 获取日报的数据
     */
    public void getDailyListData(final CallBack callBack) {


        HttpUtils.getInstance().buildService(ZhihuApis.zhihuUrl, ZhihuApis.class).getLatestList().compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <DailyListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

                compositeDisposable.add(d);
            }

            @Override
            public void onNext(DailyListBean value) {

                callBack.updateDataSuccess(value);
                Log.d(TAG, "onNext: ");
            }

            @Override
            public void onError(Throwable e) {
                callBack.updateDataFailed(e.getMessage());
                Log.d(TAG, "onError: ");

            }

            @Override
            public void onComplete() {

            }
        });


    }

    /**
     * @param date     --20190710
     * @param callBack
     */
    public void getDailyBeforeData(String date, final CallBack callBack) {

        HttpUtils.getInstance().buildService(ZhihuApis.zhihuUrl, ZhihuApis.class).getDailyBeforeList(date).compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <DailyBeforeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DailyBeforeBean value) {

                Log.d(TAG, "onNext: " + value);
                callBack.updateDataSuccess(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
                callBack.updateDataFailed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
