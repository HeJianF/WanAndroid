package com.hjf.wanandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hjf.wanandroid.app.WanApplication;

/**
 * @author heJianfeng
 * @date 2018/12/19
 */
public class NetUtils {

    private NetUtils() {
    }

    /**
     * 判断网络连接是否存在
     */
    public static boolean isAvailable() {
        try {
            ConnectivityManager manager = (ConnectivityManager) WanApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            return !(info == null || !info.isAvailable());
        } catch (Exception e) {
            // log in file
        }
        return false;
    }
}
