package com.example.geekdemo.base;

import android.app.Application;

import com.example.geekdemo.dao.DaoMaster;
import com.example.geekdemo.dao.DaoSession;

public class BaseApplication extends Application {

    private static BaseApplication context;
    private static DaoSession daoSession;

    public static BaseApplication getInstance() {
        return context;
    }

    public static DaoSession getDaoSession() {

        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        initDb();
    }

    private void initDb() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "collection.db");

        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        daoSession = daoMaster.newSession();

    }

}
