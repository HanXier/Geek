package com.example.geekdemo.mvp.zhihu;

import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.BasePresenter;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.bean.DailyBeforeBean;
import com.example.geekdemo.bean.DailyListBean;

public class DailyPresenter extends BasePresenter <DailyView, DailyModel> {


    public DailyPresenter(DailyView view) {
        super(view);
    }

    @Override
    protected DailyModel initModel() {
        return new DailyModel();
    }

    public void getCurrListData() {

        model.getDailyListData(new CallBack <DailyListBean>() {
            @Override
            public void updateDataSuccess(DailyListBean dailyListBean) {
                view.getCurrData(dailyListBean);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }


    public void getDailyBeforeData(String date) {
        model.getDailyBeforeData(date, new CallBack <DailyBeforeBean>() {
            @Override
            public void updateDataSuccess(DailyBeforeBean dailyBeforeBean) {
                view.getBeforeData(dailyBeforeBean);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }
}
