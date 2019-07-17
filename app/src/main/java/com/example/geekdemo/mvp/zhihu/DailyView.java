package com.example.geekdemo.mvp.zhihu;

import com.example.geekdemo.base.BaseView;
import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;

public interface DailyView extends BaseView {

    void getCurrData(DailyListBean dailyListBean);

    // 获取以前日报数据
    void getBeforeData(DailyBeforeBean dailyBeforeBean);

}
