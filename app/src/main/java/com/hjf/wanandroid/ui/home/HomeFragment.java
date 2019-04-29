package com.hjf.wanandroid.ui.home;

import android.view.View;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.adapter.BaseAdapter;
import com.hjf.wanandroid.adapter.HomeAdapter;
import com.hjf.wanandroid.base.list.fragment.BaseListFragment;
import com.hjf.wanandroid.been.CommonItem;
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
        ToastUtils.showToast("重新加载中...");
        mPresenter.start();
    }

    @Override
    public void onFooterRetryListener() {
        ToastUtils.showToast("重新加载中...");
        mPresenter.onLoadingMore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_banner_img:
                Object object = v.getTag(R.id.item_banner);
                if (object instanceof String) {
                    String url = (String) object;
                    WebActivity.start(getContext(), url);
                }
                break;
            default:
        }
    }

}
