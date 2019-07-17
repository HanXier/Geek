package com.example.geekdemo.base;

public interface CallBack<T> {

    void updateDataSuccess(T t);

    void updateDataFailed(String error);
}
