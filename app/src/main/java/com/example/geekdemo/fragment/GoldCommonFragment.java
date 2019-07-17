package com.example.geekdemo.fragment;

import android.util.Log;

import com.example.geekdemo.R;
import com.example.geekdemo.adapter.GankAdapter;
import com.example.geekdemo.base.BaseMvpFragment;
import com.example.geekdemo.bean.GankBean;
import com.example.geekdemo.mvp.gank.GankPresenter;
import com.example.geekdemo.mvp.gank.GankView;

public class GoldCommonFragment extends BaseMvpFragment <GankPresenter> implements GankView {


    private static final String TAG = "GoldCommonFragment";
    private GankAdapter gankAdapter;

    @Override
    protected GankPresenter initMvp() {
        return new GankPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();


    }

    @Override
    protected int getLayout() {
        return R.layout.gank__fragment;
    }


    @Override
    public void showErrorMsg(String error) {

        Log.d(TAG, "showErrorMsg: ");
    }


    @Override
    public void getGankListData(GankBean gankBean) {


    }
}
