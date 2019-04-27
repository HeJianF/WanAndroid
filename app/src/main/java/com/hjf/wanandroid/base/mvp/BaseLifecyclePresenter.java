package com.hjf.wanandroid.base.mvp;

import com.hjf.wanandroid.base.mvp.rxlifecycle.PresenterEvent;
import com.hjf.wanandroid.base.mvp.rxlifecycle.RxLifecyclePresenter;
import com.trello.rxlifecycle3.LifecycleProvider;
import com.trello.rxlifecycle3.LifecycleTransformer;
import com.trello.rxlifecycle3.RxLifecycle;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 继承此类，可以实现自动管理MVP的生命周期
 *
 * @author heJianfeng
 * @date 2019/3/22
 */
public abstract class BaseLifecyclePresenter<V extends MvpView> extends BasePresenter<V> implements LifecycleProvider<Integer> {

    private final BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();

    public abstract void start();

    @Override
    public void attachView(V mvpView) {
        super.attachView(mvpView);
        behaviorSubject.onNext(PresenterEvent.ATTACH);
    }

    @Override
    public void detachView() {
        super.detachView();
        behaviorSubject.onNext(PresenterEvent.DETACH);
    }

    @NonNull
    @Override
    public Observable<Integer> lifecycle() {
        return behaviorSubject.cache();
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecyclePresenter.bindPresenter(behaviorSubject);
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull Integer event) {
        return RxLifecycle.bindUntilEvent(behaviorSubject, event);
    }
}

