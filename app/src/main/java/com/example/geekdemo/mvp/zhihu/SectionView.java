package com.example.geekdemo.mvp.zhihu;

import com.example.geekdemo.base.BaseView;
import com.example.geekdemo.bean.SectionListBean;

public interface SectionView extends BaseView {

    void getSectionListBean(SectionListBean sectionListBean);
}
