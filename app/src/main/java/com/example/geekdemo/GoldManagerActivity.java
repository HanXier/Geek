package com.example.geekdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.geekdemo.adapter.GoldManagerAdapter;
import com.example.geekdemo.base.BaseActivity;
import com.example.geekdemo.utils.Constants;
import com.example.geekdemo.utils.DefaultItemTouchHelpCallback;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldManagerActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private DefaultItemTouchHelpCallback mCallback;
    private GoldManagerAdapter goldManagerAdapter;

    @Override
    protected void initView() {
        super.initView();


        toolBar.setTitle("选择tab");

        setSupportActionBar(toolBar);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        goldManagerAdapter = new GoldManagerAdapter(this);

        recyclerview.setAdapter(goldManagerAdapter);


        mCallback = new DefaultItemTouchHelpCallback(new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
            }


            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                // 判断 集合不为null
                if (Constants.goldStatuses != null) {
                    //交换 集合里  源位置  和 目标位置
                    Collections.swap(Constants.goldStatuses, srcPosition, targetPosition);
                    goldManagerAdapter.notifyItemMoved(srcPosition, targetPosition);
                    return true;
                }
                return false;
            }
        });
        mCallback.setDragEnable(true);
        mCallback.setSwipeEnable(false);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);

    }

    @Override
    protected void initMvp() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_gold_manager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();

        intent.setAction("update.gold");

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}
