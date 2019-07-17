package com.example.geekdemo.base;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {

    protected V view;
    protected M model;

    List <M> modleList = new ArrayList <>();

    public BasePresenter(V view) {
        this.view = view;
        this.model = initModel();
        modleList.add(model);
    }

    protected abstract M initModel();

    public void destory() {

        view = null;
        // 切断网络
        if (modleList != null) {
            for (M model : modleList) {
                model.destory();
            }
        }

    }
}
