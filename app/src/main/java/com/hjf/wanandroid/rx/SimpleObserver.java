package com.hjf.wanandroid.rx;

import com.hjf.wanandroid.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author heJianfeng
 * @date 2018/12/19
 * <p>
 * 对数据做处理
 */
public class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T t) {
        onHandleSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(e, NetUtils.isAvailable());
    }

    @Override
    public void onComplete() {
    }

    /**
     * 处理接口数据返回 success
     *
     * @param t
     */
    protected void onHandleSuccess(T t) {
    }

    /**
     * 处理接口数据返回 Error
     *
     * @param e
     * @param netAvailable
     */
    protected void onHandleError(Throwable e, boolean netAvailable) {
    }

}
