package com.hjf.wanandroid.base;

import android.os.Bundle;

import com.hjf.wanandroid.base.mvp.BasePresenter;
import com.hjf.wanandroid.base.mvp.MvpView;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.annotation.Nullable;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements MvpView {

    protected P mPresenter;
    private boolean isAlive;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected P providePresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
}
