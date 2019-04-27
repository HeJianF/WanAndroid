package com.hjf.wanandroid.net;

import com.hjf.wanandroid.been.ArticleInfo;
import com.hjf.wanandroid.been.BannerInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public interface WanAndroidApi {

    @GET("/article/list/{page}/json")
    Observable<ArticleInfo> getArticle(@Path("page") int page);

    @GET("/banner/json")
    Observable<BannerInfo> getBanner();
}
