package com.example.geekdemo.mvp.zhihu;

import com.example.geekdemo.base.BaseModel;
import com.example.geekdemo.base.BasePresenter;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.bean.SectionListBean;

public class SectionPresenter extends BasePresenter <SectionView, SectionModel> {


    public SectionPresenter(SectionView view) {
        super(view);
    }

    @Override
    protected SectionModel initModel() {
        return new SectionModel();
    }

    public void getSectionList() {

        model.getSectionListData(new CallBack <SectionListBean>() {
            @Override
            public void updateDataSuccess(SectionListBean sectionListBean) {

                view.getSectionListBean(sectionListBean);
            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });
    }
}
