package com.hjf.wanandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.hjf.wanandroid.been.CommonItem;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.DividerHolder;
import com.hjf.wanandroid.vh.home.ArticleVH;
import com.hjf.wanandroid.vh.home.BannerVH;

import androidx.annotation.NonNull;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class HomeAdapter extends BaseAdapter<CommonItem> {

    public static final int HOME_ARTICLE = 0;
    public static final int HOME_BANNER = 1;

    private View.OnClickListener listener;

    public HomeAdapter(Context context, View.OnClickListener listener) {
        super(context);
        this.listener = listener;
        onShowLoading();
    }

    @Override
    protected BaseViewHolder<CommonItem> onCreateViewHolderInner(ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        switch (viewType) {
            case HOME_BANNER:
                holder = new BannerVH(parent, listener);
                ((BannerVH) holder).setLoopVpViewHeight(2.5F);
                break;
            case HOME_ARTICLE:
                holder = new ArticleVH(parent, listener);
                break;
            default:
                holder = new DividerHolder(parent);
        }
        return holder;
    }

    @Override
    protected int getItemViewTypeInner(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position).type;
        }
        return super.getItemViewTypeInner(position);
    }

    @Override
    protected void onBindViewHolderInner(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.bind(mList.get(i).object, i);
    }
}
