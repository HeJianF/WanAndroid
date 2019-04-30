package com.hjf.wanandroid.vh;

import android.view.ViewGroup;
import android.widget.TextView;

import com.hjf.wanandroid.R;

import butterknife.BindView;

/**
 * @author heJianfeng
 * @date 2019-04-29
 */
public class EmptyViewHolder extends BaseViewHolder<String> {

    @BindView(R.id.tv_empty)
    TextView tv_empty;

    public EmptyViewHolder(ViewGroup parent) {
        super(R.layout.vh_common_empty, parent);
    }

    @Override
    public void bind(String data, int position) {
        tv_empty.setText(data);
    }
}
