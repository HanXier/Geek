package com.example.geekdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {


    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(getLayout(), null);
        bind = ButterKnife.bind(this, root);


        initPresenter();
        initView();
        initListener();
        return root;

    }

    protected void initListener() {

    }

    protected void initView() {
    }


    protected abstract void initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        bind.unbind();
    }

    protected abstract int getLayout();
}
