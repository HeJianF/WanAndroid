package com.hjf.wanandroid.ui.home;

import com.hjf.wanandroid.base.mvp.MvpView;
import com.hjf.wanandroid.been.CommonItem;

import java.util.List;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public interface HomeCallBack extends MvpView {

    void showContent(List<CommonItem> data);

    void showErrorPage(String message);

    void showEmptyPage(String emptyInfo);
}
