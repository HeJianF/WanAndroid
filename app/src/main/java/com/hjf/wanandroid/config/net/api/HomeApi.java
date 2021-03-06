package com.hjf.wanandroid.config.net.api;

import com.hjf.wanandroid.entity.ArticleInfo;
import com.hjf.wanandroid.entity.BannerInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public interface HomeApi {

    @GET("/article/list/{page}/json")
    Observable<ArticleInfo> getArticle(@Path("page") int page);

    @GET("/banner/json")
    Observable<BannerInfo> getBanner();
}
