package com.hjf.wanandroid.ui.home;

import com.hjf.wanandroid.adapter.HomeAdapter;
import com.hjf.wanandroid.base.mvp.BaseLifecyclePresenter;
import com.hjf.wanandroid.base.mvp.rxlifecycle.PresenterEvent;
import com.hjf.wanandroid.been.ArticleInfo;
import com.hjf.wanandroid.been.CommonItem;
import com.hjf.wanandroid.config.net.api.ApiFactory;
import com.hjf.wanandroid.config.net.api.HomeApi;
import com.hjf.wanandroid.rx.SimpleObserver;
import com.hjf.wanandroid.utils.CommonUtil;
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
        HomeApi homeApi = ApiFactory.getHomeApi();
        Observable.zip(homeApi.getBanner(), homeApi.getArticle(0), (bannerInfo, articleInfo) -> {
            List<CommonItem> list = new ArrayList<>();

            //Banner轮播图
            if (bannerInfo != null && Constant.ERROR_CODE_0 == bannerInfo.getErrorCode()) {
                if (CommonUtil.noEmpty(bannerInfo.getData())) {
                    CommonItem banner = new CommonItem(HomeAdapter.HOME_BANNER, bannerInfo.getData());
                    list.add(banner);
                }
            }

            //文章列表
            if (articleInfo != null && Constant.ERROR_CODE_0 == articleInfo.getErrorCode()) {
                if (CommonUtil.noEmpty(articleInfo.getData().getDatas())) {
                    List<ArticleInfo.DataBean.DatasBean> datasBeanList = articleInfo.getData().getDatas();
                    for (ArticleInfo.DataBean.DatasBean datasBean : datasBeanList) {
                        CommonItem article = new CommonItem(HomeAdapter.HOME_ARTICLE, datasBean);
                        list.add(article);
                    }
                }
            }

            return list;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(PresenterEvent.DETACH))
                .subscribe(new SimpleObserver<List<CommonItem>>() {
                    @Override
                    protected void onHandleSuccess(List<CommonItem> commonItems) {
                        getMvpView().showContent(commonItems);
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
