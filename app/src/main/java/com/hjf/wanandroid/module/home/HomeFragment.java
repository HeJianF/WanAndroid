package com.hjf.wanandroid.module.home;

import android.view.View;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.base.BaseAdapter;
import com.hjf.wanandroid.base.list.fragment.BaseListFragment;
import com.hjf.wanandroid.entity.CommonItem;
import com.hjf.wanandroid.ui.WebActivity;
import com.hjf.wanandroid.utils.ToastUtils;

/**
 * @author heJianfeng
 * @date 2019-04-29
 */
public class HomeFragment extends BaseListFragment<CommonItem, HomePresenter> implements View.OnClickListener {

    @Override
    public HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected BaseAdapter<CommonItem> provideAdapter() {
        return new HomeAdapter(getContext(), this);
    }

    @Override
    public void onRetryListener() {
        ToastUtils.showToast(getString(R.string.re_loading));
        mPresenter.start();
    }

    @Override
    public void onFooterRetryListener() {
        ToastUtils.showToast(getString(R.string.re_loading));
        mPresenter.onLoadingMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_banner:
            case R.id.item_article:
                Object object = v.getTag(R.id.link_url);
                if (object instanceof String) {
                    String url = (String) object;
                    WebActivity.start(getContext(), url);
                }
                break;
            default:
        }
    }
}
