package com.example.geekdemo.mvp.gank;

import com.example.geekdemo.api.GankApis;
import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.base.net.HttpUtils;
import com.example.geekdemo.bean.GankBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GankModel extends BaseModel {

    public void getGankList(String key, final CallBack callBack) {

        HttpUtils.getInstance().buildService(GankApis.HOST, GankApis.class).getTechList(key, 20, 1).compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <GankBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(GankBean value) {

                callBack.updateDataSuccess(value);
            }

            @Override
            public void onError(Throwable e) {

                callBack.updateDataFailed(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });


    }
}
