package com.hjf.wanandroid.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.ErrorViewHolder;
import com.hjf.wanandroid.vh.LoadingViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public abstract class BaseAdapter<E> extends RecyclerView.Adapter<BaseViewHolder> {

    protected static final int TYPE_ERROR = -3; //错误
    protected static final int TYPE_LOADING = -4;//正在加载
    protected boolean isError;
    protected boolean isLoading;
    protected String mErrorString = "";

    List<E> mList = new ArrayList<>();

    public BaseAdapter() {
        isError = false;
        isLoading = false;
    }

    /**
     * 更新数据
     *
     * @param list
     */
    public void setmList(List<E> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        isError = false;
        isLoading = false;
        clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_LOADING:
                return new LoadingViewHolder(viewGroup);
            case TYPE_ERROR:
                return new ErrorViewHolder(viewGroup, onClickListener);
            default:
        }
        return onCreateViewHolderInner(viewGroup, i);
    }

    protected abstract BaseViewHolder<E> onCreateViewHolderInner(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder instanceof LoadingViewHolder) {
            return;
        }
        if (baseViewHolder instanceof ErrorViewHolder) {
            ((ErrorViewHolder) baseViewHolder).bind(mErrorString, i);
            return;
        }
        onBindViewHolderInner(baseViewHolder, i);
    }

    protected void onBindViewHolderInner(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.bind(getItemInfo(i), i);
    }

    private E getItemInfo(int position) {
        if (position >= 0 && position < mList.size()) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (isError || isLoading) {
            return 1;
        }
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoading) {
            return TYPE_LOADING;
        }
        if (isError) {
            return TYPE_ERROR;
        }
        return getItemViewTypeInner(position);
    }

    protected int getItemViewTypeInner(int position) {
        return 0;
    }

    public void onShowError(String errorStirng) {
        clear();
        isError = true;
        isLoading = false;
        this.mErrorString = errorStirng;
        notifyDataSetChanged();
    }

    public void onShowLoading() {
        clear();
        isError = false;
        isLoading = true;
        notifyDataSetChanged();
    }

    public void clear() {
        mList.clear();
    }

    private OnAdapterErrorListener errorListener;

    public void setErrorListener(OnAdapterErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fl_error:
                    errorListener.onRetryListener();
                    break;
                default:
            }
        }
    };

    public interface OnAdapterErrorListener {
        void onRetryListener();
    }

}
