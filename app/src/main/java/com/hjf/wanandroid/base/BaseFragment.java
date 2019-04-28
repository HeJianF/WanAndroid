package com.hjf.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjf.wanandroid.base.mvp.BasePresenter;
import com.hjf.wanandroid.base.mvp.MvpView;
import com.trello.rxlifecycle3.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements MvpView {

    protected Context mContext;
    protected View mView;
    protected P mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) mView = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, mView);
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initOnCreateView();
        return mView;
    }

    public abstract P providePresenter();

    public abstract int provideLayoutId();

    protected abstract void initOnCreateView();

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public boolean isAlive() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            return true;
        }
        return isAdded();
    }
}
