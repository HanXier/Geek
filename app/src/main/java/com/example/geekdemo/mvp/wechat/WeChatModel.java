package com.example.geekdemo.mvp.zhihu.wechat;

import android.util.Log;

import com.example.geekdemo.api.WeChatApis;
import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.base.net.HttpUtils;
import com.example.geekdemo.bean.Newslist;
import com.example.geekdemo.bean.WechatResult;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WeChatModel extends BaseModel {


    private static final String TAG = "WeChatModel";

    public void getWechatList(String key, String num, String page, final CallBack callBack) {

        HttpUtils.getInstance().buildService(WeChatApis.wechatUrl, WeChatApis.class).getWechatList(key, num, page).compose(HttpUtils.schedulersTransformer()).subscribe(new Observer <WechatResult <List <Newslist>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(WechatResult <List <Newslist>> value) {

                Log.d(TAG, "onNext: ");
                if (value.getCode() == 200) {
                    callBack.updateDataSuccess(value);

                } else {
                    callBack.updateDataFailed(value.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "onError: ");
                callBack.updateDataFailed(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });


    }
}
