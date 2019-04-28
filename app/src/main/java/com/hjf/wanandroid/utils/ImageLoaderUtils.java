package com.hjf.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public class ImageLoaderUtils {

    private ImageLoaderUtils() {
    }

    private static volatile ImageLoaderUtils instance;

    public static ImageLoaderUtils instance() {
        if (instance == null) {
            synchronized (ImageLoaderUtils.class) {
                if (instance == null) {
                    instance = new ImageLoaderUtils();
                }
            }
        }
        return instance;
    }

    public void disImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
