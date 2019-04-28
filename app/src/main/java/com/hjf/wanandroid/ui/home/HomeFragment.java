package com.hjf.wanandroid.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.adapter.BaseAdapter;
import com.hjf.wanandroid.adapter.HomeAdapter;
import com.hjf.wanandroid.base.BaseFragment;
import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeCallBack, View.OnClickListener, BaseAdapter.OnAdapterErrorListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    private HomeAdapter adapter;

    @Override
    public HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    public int provideLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initOnCreateView() {
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new HomeAdapter(this);
        adapter.setErrorListener(this);
        recycle_view.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showContent(List<WanAndroidInfo> data) {
        adapter.setmList(data);
    }

    @Override
    public void showErrorPage(String message) {
        adapter.onShowError(message);
    }

    @Override
    public void showEmptyPage(String emptyInfo) {
    }

    @Override
    public void onRetryListener() {
        ToastUtils.showToast("重新加载中...");
        mPresenter.loadData();
    }
}
