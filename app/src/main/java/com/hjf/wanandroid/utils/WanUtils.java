package com.hjf.wanandroid.utils;

/**
 * @author heJianfeng
 * @date 2019-04-29
 */
public class WanUtils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
