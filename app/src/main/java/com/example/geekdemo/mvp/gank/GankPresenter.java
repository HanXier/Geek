package com.example.geekdemo.mvp.gank;

import com.example.geekdemo.base.BasePresenter;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.bean.GankBean;

public class GankPresenter extends BasePresenter <GankView, GankModel> {

    public GankPresenter(GankView view) {
        super(view);
    }

    @Override
    protected GankModel initModel() {
        return new GankModel();
    }

    public void getGankList(String key) {

        model.getGankList(key, new CallBack <GankBean>() {
            @Override
            public void updateDataSuccess(GankBean o) {
                view.getGankListData(o);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }
}
