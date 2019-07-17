package com.example.geekdemo.utils;

import com.example.geekdemo.base.BaseApplication;
import com.example.geekdemo.bean.CollectionDbBean;
import com.example.geekdemo.dao.CollectionDbBeanDao;
import com.example.geekdemo.dao.DaoSession;

import java.util.List;

public class DbUtils {

    public static void insertBean(CollectionDbBean collectionDbBean) {

        DaoSession daoSession = BaseApplication.getDaoSession();

        CollectionDbBean dbBean = queryOne(collectionDbBean);
        if (dbBean == null) {
            daoSession.insert(collectionDbBean);
        }

    }

    public static void delete(CollectionDbBean collectionDbBean) {
        DaoSession daoSession = BaseApplication.getDaoSession();

        CollectionDbBean dbBean = queryOne(collectionDbBean);
        if (dbBean != null) {
            daoSession.delete(dbBean);
        }

    }


    public static List <CollectionDbBean> queryAll() {

        return BaseApplication.getDaoSession().loadAll(CollectionDbBean.class);
    }


    public static CollectionDbBean queryOne(CollectionDbBean collectionDbBean) {

        DaoSession daoSession = BaseApplication.getDaoSession();

        return daoSession.queryBuilder(CollectionDbBean.class).where(CollectionDbBeanDao.Properties.Id.eq(collectionDbBean.getId())).build().unique();


    }
}
