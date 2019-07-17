package com.example.geekdemo.mvp.zhihu.wechat;

import com.example.geekdemo.base.BaseView;
import com.example.geekdemo.bean.Newslist;

import java.util.List;

public interface WeChatView extends BaseView {


    void updateWeChatList(List <Newslist> weChatList);
}
