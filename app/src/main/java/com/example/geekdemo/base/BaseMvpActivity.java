package com.example.geekdemo.base;

/**
 * 抽取P
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void initMvp() {

        mPresenter = initPresenter();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.destory();

    }
}
