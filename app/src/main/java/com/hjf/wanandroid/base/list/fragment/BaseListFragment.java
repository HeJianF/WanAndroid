package com.hjf.wanandroid.base.list.fragment;

import com.hjf.wanandroid.base.BaseFragment;
import com.hjf.wanandroid.base.mvp.BasePresenter;

import java.util.List;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public abstract class BaseListFragment<P extends LoadingMoreScrollListenerM> extends BaseFragment implements IListMvpView {

    @Override
    public BasePresenter providePresenter() {
        return null;
    }

    @Override
    public int provideLayoutId() {
        return 0;
    }

    @Override
    protected void initOnCreateView() {

    }

    @Override
    public void showContent(List data) {

    }

    @Override
    public void showErrorPage(String message, boolean refresh) {

    }

    @Override
    public void showEmptyPage(String emptyInfo, boolean refresh) {

    }
}
