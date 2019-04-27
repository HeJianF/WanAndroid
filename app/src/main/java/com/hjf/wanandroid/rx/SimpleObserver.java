package com.hjf.wanandroid.rx;

import android.util.Log;

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
        Log.d("SimpleObserver", "onSubscribe: ");
    }

    @Override
    public void onNext(T t) {
        Log.d("SimpleObserver", "onNext: ");
        onHandleSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        Log.d("SimpleObserver", "onError: " + e.getMessage());
        onHandleError(e, NetUtils.isAvailable());
    }

    @Override
    public void onComplete() {
        Log.d("SimpleObserver", "onComplete: ");
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
