package com.hjf.wanandroid.base.list.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjf.wanandroid.adapter.BaseAdapter;

public class LoadingMoreScrollListenerM extends RecyclerView.OnScrollListener {
    private static final int LAST_VISIBLE_COUNT_TO_LOADING = 2;

    private boolean isLoading = false;
    private OnLoadingMoreListenerM mListener;

    public LoadingMoreScrollListenerM(OnLoadingMoreListenerM mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        // 如果页面在加载的话不用调用
        if (recyclerView.getAdapter() instanceof BaseAdapter) {
            BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();
            if (adapter.isLoading || adapter.isError || adapter.getFooterHolder().isNoMore()) {
                return;
            }
        }

        //LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int itemCount = -1;
        int lastVisibleItemPosition = -1;
        int mVisibleCount = -1;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            mVisibleCount = lastVisibleItemPosition - layoutManager.findFirstVisibleItemPosition() + 1;
        }

        // 最后一个可见时加载数据
        if (!isLoading && dy >= 0 && (lastVisibleItemPosition + LAST_VISIBLE_COUNT_TO_LOADING >= itemCount)) {
            isLoading = true;

            if (mListener != null) {
                mListener.onLoadingMore();
            }
        }
    }

    public void loadingFinish() {
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public interface OnLoadingMoreListenerM {
        void onLoadingMore();
    }
}