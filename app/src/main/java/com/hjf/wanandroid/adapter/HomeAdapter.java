package com.hjf.wanandroid.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.utils.Constant;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.home.ArticleVH;
import com.hjf.wanandroid.vh.home.BannerVH;

import androidx.annotation.NonNull;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class HomeAdapter extends BaseAdapter<WanAndroidInfo> {

    private View.OnClickListener listener;

    public HomeAdapter(View.OnClickListener listener) {
        super();
        this.listener = listener;
        onShowLoading();
    }

    @Override
    protected BaseViewHolder<WanAndroidInfo> onCreateViewHolderInner(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.HOME_ARTICLE:
                return new ArticleVH(parent, listener);
            case Constant.HOME_BANNER:
                BannerVH bannerVH = new BannerVH(parent, listener);
                bannerVH.setLoopVpViewHeight(2.5F);
                return bannerVH;
            default:
                return null;
        }
    }

    @Override
    protected int getItemViewTypeInner(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position).getType();
        }
        return super.getItemViewTypeInner(position);
    }

    @Override
    protected void onBindViewHolderInner(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder instanceof ArticleVH) {
            ArticleVH articleVH = (ArticleVH) baseViewHolder;
            articleVH.bind(mList.get(i));
        }
        super.onBindViewHolderInner(baseViewHolder, i);
    }
}
