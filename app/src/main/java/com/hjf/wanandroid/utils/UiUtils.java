package com.hjf.wanandroid.utils;

import com.hjf.wanandroid.app.WanApplication;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class UiUtils {

    public static float dip2px(float dipValue) {
        float scale = WanApplication.getApplication().getResources().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

}