package com.example.geekdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        ButterKnife.bind(this);

        initMvp();
        initView();
        initListener();

    }

    protected void initView() {
    }

    protected abstract void initMvp();

    protected abstract void initListener();

    protected abstract int getLayout();


}
