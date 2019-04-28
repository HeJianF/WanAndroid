package com.hjf.wanandroid.utils;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public class ScreenUtil {

    private ScreenUtil() {
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth() {
        return UiUtils.metrics == null ? 0 : UiUtils.metrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight() {
        return UiUtils.metrics == null ? 0 : UiUtils.metrics.heightPixels;
    }
}
