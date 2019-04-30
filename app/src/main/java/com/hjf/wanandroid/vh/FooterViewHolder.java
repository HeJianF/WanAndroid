package com.hjf.wanandroid.vh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hjf.wanandroid.R;

import butterknife.BindView;

/**
 * Created by baiiu on 2015/11/21.
 * ListView的脚布局，提供三种状态的设置
 */
public class FooterViewHolder extends BaseViewHolder<Integer> {
    public static final int HAS_MORE = 0;
    public static final int NO_MORE = 1;
    public static final int ERROR = 2;
    public static final int GONE = 3;

    private int mCurrentState = -1;

    @BindView(R.id.rl_loading)
    ViewGroup loadingView;
    @BindView(R.id.rl_error)
    ViewGroup errorView;
    @BindView(R.id.rl_no_more)
    ViewGroup noMoreView;
    @BindView(R.id.fl_container)
    FrameLayout mRootView;
    @BindView(R.id.img_loadmore_line1)
    View line1;
    @BindView(R.id.img_loadmore_line2)
    View line2;

    public FooterViewHolder(Context context, View.OnClickListener listener) {
        super(LayoutInflater.from(context).inflate(R.layout.vh_common_footer, null));
        errorView.setOnClickListener(listener);
    }

    public FooterViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.vh_common_footer, null));
    }

    public void bind(Integer data) {
        bind(data, -1);
    }

    @Override
    public void bind(Integer data, int position) {
        mRootView.setVisibility(data == View.GONE ? View.GONE : View.VISIBLE);

        if (mCurrentState == data) {
            return;
        }

        mCurrentState = data;

        loadingView.setVisibility(data == HAS_MORE ? View.VISIBLE : View.INVISIBLE);
        noMoreView.setVisibility(data == NO_MORE ? View.VISIBLE : View.INVISIBLE);
        errorView.setVisibility(data == ERROR ? View.VISIBLE : View.INVISIBLE);
    }

    public void setLineColor(int color) {
        line1.setBackgroundColor(color);
        line2.setBackgroundColor(color);
    }

    public FrameLayout getRootView() {
        return mRootView;
    }

    public void showLoading() {
        bind(HAS_MORE, -1);
    }

    public void showError() {
        bind(ERROR, -1);
    }

    public void showNoMore() {
        bind(NO_MORE, -1);
    }

    public boolean isNoMore() {
        return mCurrentState == NO_MORE;
    }

    public boolean isError() {
        return mCurrentState == ERROR;
    }

    public boolean hasMore() {
        return mCurrentState == HAS_MORE;
    }
}
