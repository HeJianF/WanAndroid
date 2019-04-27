package com.hjf.wanandroid.vh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {

    public View.OnClickListener mListener;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public BaseViewHolder(@LayoutRes int layoutId, ViewGroup parent) {
        this(layoutId, parent, null);
    }

    public BaseViewHolder(@LayoutRes int layoutId, ViewGroup parent, View.OnClickListener listener) {
        this(layoutId, parent, listener, true);
    }

    public BaseViewHolder(@LayoutRes int layoutId, ViewGroup parent, View.OnClickListener listener,
                          boolean allClick) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        //注册ButterKnife
        ButterKnife.bind(this, itemView);
        if (listener != null) {
            this.mListener = listener;
            if (allClick) {
                itemView.setOnClickListener(listener);
            }
        }
    }

    /**
     * 绑定数据
     *
     * @param data
     */
    public abstract void bind(E data);

}
