package com.hjf.wanandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.utils.CommonUtil;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.ErrorViewHolder;
import com.hjf.wanandroid.vh.FooterViewHolder;
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

    protected static final int TYPE_FOOTER = -1;
    protected static final int TYPE_ERROR = -3; //错误
    protected static final int TYPE_LOADING = -4;//正在加载
    public boolean isError;
    public boolean isLoading;
    protected String mErrorString = "";
    protected Context mContext;
    public static int MIN_COUNT_SHOW_FOOTER = 10;
    protected FooterViewHolder mFooterViewHolder;
    List<E> mList = new ArrayList<>();

    public BaseAdapter(Context context) {
        this.mContext = context;
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
            case TYPE_FOOTER:
                return getFooterHolder();
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
            baseViewHolder.bind(mErrorString, i);
            return;
        }
        if (baseViewHolder instanceof FooterViewHolder) {
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

        if (CommonUtil.isEmpty(mList)) {
            return 0;
        }

        //添加FooterViewHolder
        int size = mList.size();
        if (size > MIN_COUNT_SHOW_FOOTER) {
            return size + 1;
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
        if (mList != null && mList.size() > MIN_COUNT_SHOW_FOOTER && position == mList.size()) {
            return TYPE_FOOTER;
        }
        return getItemViewTypeInner(position);
    }

    protected int getItemViewTypeInner(int position) {
        return 0;
    }

    public FooterViewHolder getFooterHolder() {
        if (mFooterViewHolder == null) {
            mFooterViewHolder = new FooterViewHolder(mContext, onClickListener);
            FrameLayout footerView = mFooterViewHolder.getRootView();
            footerView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        }
        return mFooterViewHolder;
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
