package com.hjf.wanandroid.ui.home;

import com.hjf.wanandroid.base.mvp.BaseLifecyclePresenter;
import com.hjf.wanandroid.base.mvp.rxlifecycle.PresenterEvent;
import com.hjf.wanandroid.been.ArticleInfo;
import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.net.Retrofit2Create;
import com.hjf.wanandroid.net.WanAndroidApi;
import com.hjf.wanandroid.rx.SimpleObserver;
import com.hjf.wanandroid.ui.home.HomeCallBack;
import com.hjf.wanandroid.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class HomePresenter extends BaseLifecyclePresenter<HomeCallBack> {

    @Override
    public void start() {
        loadData();
    }

    public void loadData() {
        WanAndroidApi wanAndroidApi = Retrofit2Create.WAN_ANDEOID.create(WanAndroidApi.class);
        Observable.zip(wanAndroidApi.getBanner(), wanAndroidApi.getArticle(0), (bannerInfo, articleInfo) -> {
            List<WanAndroidInfo> wanAndroidInfos = new ArrayList<>();
            //Banner轮播图
            if (Constant.ERROR_CODE_0 == bannerInfo.getErrorCode()) {
                WanAndroidInfo banner = new WanAndroidInfo(Constant.HOME_BANNER, bannerInfo);
                wanAndroidInfos.add(banner);
            }
            //文章列表
            if (Constant.ERROR_CODE_0 == articleInfo.getErrorCode()) {
                List<ArticleInfo.DataBean.DatasBean> datasBeanList = articleInfo.getData().getDatas();
                for (ArticleInfo.DataBean.DatasBean datasBean : datasBeanList) {
                    WanAndroidInfo article = new WanAndroidInfo(Constant.HOME_ARTICLE, datasBean);
                    wanAndroidInfos.add(article);
                }
            }
            return wanAndroidInfos;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(PresenterEvent.DETACH))
                .subscribe(new SimpleObserver<List<WanAndroidInfo>>() {
                    @Override
                    protected void onHandleSuccess(List<WanAndroidInfo> wanAndroidInfos) {
                        getMvpView().showContent(wanAndroidInfos);
                    }

                    @Override
                    protected void onHandleError(Throwable e, boolean netAvailable) {
                        if (netAvailable) {
                            getMvpView().showErrorPage(e.getMessage());
                        } else {
                            getMvpView().showErrorPage("网络未连接     " + e.getMessage());
                        }
                    }
                });
    }
}
