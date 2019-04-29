package com.hjf.wanandroid.base.list.fragment;

import com.hjf.wanandroid.base.mvp.MvpView;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public interface IListMvpView<C extends List> extends MvpView {

    void showContent(C data, boolean refresh);

    void showErrorPage(String message, boolean refresh);

    void showEmptyPage(String emptyInfo);

}
