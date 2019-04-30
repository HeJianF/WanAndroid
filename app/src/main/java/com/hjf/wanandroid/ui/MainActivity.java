package com.hjf.wanandroid.ui;

import android.os.Bundle;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.base.BaseActivity;
import com.hjf.wanandroid.module.home.HomeFragment;
import com.hjf.wanandroid.ui.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initOnCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeFragment()).commit();
    }
}
