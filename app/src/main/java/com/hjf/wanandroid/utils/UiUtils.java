package com.hjf.wanandroid.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hjf.wanandroid.app.WanApplication;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class UiUtils {

    public static Context getContext() {
        return WanApplication.getContext();
    }

    public static DisplayMetrics metrics = UiUtils.getContext() != null
            ? UiUtils.getContext().getResources().getDisplayMetrics()
            : Resources.getSystem().getDisplayMetrics();

    public static float dip2px(float dipValue) {
        float scale = UiUtils.getContext().getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

}
