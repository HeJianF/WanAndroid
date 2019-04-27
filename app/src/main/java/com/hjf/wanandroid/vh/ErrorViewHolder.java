package com.hjf.wanandroid.vh;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjf.wanandroid.R;

import butterknife.BindView;

/**
 * @author heJianfeng
 * @date 2018/12/17
 */
public class ErrorViewHolder extends BaseViewHolder<String> {

    @BindView(R.id.error_text)
    TextView errorText;

    public ErrorViewHolder(ViewGroup parent, View.OnClickListener listener) {
        super(R.layout.layout_error, parent, listener, true);
        itemView.setOnClickListener(mListener);
    }

    @Override
    public void bind(String data) {
        errorText.setText(TextUtils.isEmpty(data) ? "点击屏幕，重新加载" : data);
    }

}
