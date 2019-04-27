package com.hjf.wanandroid.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-02
 */
public enum Retrofit2Create {

    /**
     * wanAndroid
     */
    WAN_ANDEOID();

    private final Retrofit retrofit;

    Retrofit2Create() {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(4, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
