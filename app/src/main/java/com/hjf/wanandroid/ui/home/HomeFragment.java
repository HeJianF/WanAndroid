package com.hjf.wanandroid.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.adapter.BaseAdapter;
import com.hjf.wanandroid.adapter.HomeAdapter;
import com.hjf.wanandroid.base.BaseFragment;
import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.utils.ToastUtils;

import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeCallBack, View.OnClickListener, BaseAdapter.OnAdapterErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Activity mActivity;
    private HomeAdapter adapter;
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new HomeAdapter(this);
        adapter.setErrorListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
