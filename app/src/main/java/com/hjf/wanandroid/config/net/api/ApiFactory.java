package com.hjf.wanandroid.config.net.api;

import com.hjf.wanandroid.config.net.Retrofit2Create;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public class ApiFactory {

    private static HomeApi homeApi;

    private static <T> T getApi(T t, Class<T> tClass) {
        return t == null ? Retrofit2Create.WAN_ANDROID.create(tClass) : t;
    }

    public static HomeApi getHomeApi() {
        return homeApi = getApi(homeApi, HomeApi.class);
    }

}
