package com.example.geekdemo.mvp.gank;

import com.example.geekdemo.base.BaseView;
import com.example.geekdemo.bean.GankBean;

public interface GankView extends BaseView {

    void getGankListData(GankBean gankBean);
}
