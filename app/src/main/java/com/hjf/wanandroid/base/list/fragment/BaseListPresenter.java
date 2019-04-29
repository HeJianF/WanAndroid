package com.hjf.wanandroid.base.list.fragment;

import com.hjf.wanandroid.base.mvp.MvpLifecyclePresenter;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2019-04-29
 */
public abstract class BaseListPresenter<C extends List> extends MvpLifecyclePresenter<IListMvpView<C>> implements LoadingMoreScrollListenerM.OnLoadingMoreListenerM {
    protected volatile int requestPage = 0;
}
