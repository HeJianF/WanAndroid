package com.hjf.wanandroid.base.mvp;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 *
 * @author heJianfeng
 * @date 2019/3/22
 */

public interface MvpView {
    /**
     * View的宿主已经被销毁了么
     */
    boolean isAlive();
}