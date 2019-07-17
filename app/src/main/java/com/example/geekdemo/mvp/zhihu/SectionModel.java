package com.example.geekdemo.mvp.zhihu;

import android.util.Log;

import com.example.geekdemo.api.ZhihuApis;
import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.base.net.HttpUtils;
import com.example.geekdemo.bean.SectionListBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SectionModel extends BaseModel {

    private static final String TAG = "SectionModel";

    public void getSectionListData(final CallBack callBack) {

        HttpUtils.getInstance().buildService(ZhihuApis.zhihuUrl, ZhihuApis.class).getSectionList().compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <SectionListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

                compositeDisposable.add(d);
            }

            @Override
            public void onNext(SectionListBean value) {

                callBack.updateDataSuccess(value);
                Log.d(TAG, "onNext: ");
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
