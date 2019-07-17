package com.example.geekdemo.mvp.zhihu.wechat;

import com.example.geekdemo.base.BasePresenter;
import com.example.geekdemo.base.CallBack;
import com.example.geekdemo.bean.Newslist;
import com.example.geekdemo.bean.WechatResult;

import java.util.List;

public class WeChatPresenter extends BasePresenter <WeChatView, WeChatModel> {


    public WeChatPresenter(WeChatView view) {
        super(view);
    }

    @Override
    protected WeChatModel initModel() {
        return new WeChatModel();
    }

    public void getWeChatListData() {

        model.getWechatList("52b7ec3471ac3bec6846577e79f20e4c", "20", "1", new CallBack <WechatResult <List <Newslist>>>() {
            @Override
            public void updateDataSuccess(WechatResult <List <Newslist>> wechatResult) {
                // bean   fragment --list

                view.updateWeChatList(wechatResult.getT());


            }

            @Override
            public void updateDataFailed(String error) {

                view.showErrorMsg(error);
            }
        });

    }
}
