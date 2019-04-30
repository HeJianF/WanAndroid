package com.hjf.wanandroid.base.list.fragment;

import android.text.TextUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.adapter.BaseAdapter;
import com.hjf.wanandroid.base.BaseFragment;
import com.hjf.wanandroid.utils.CommonUtil;
import com.hjf.wanandroid.utils.Constant;
import com.hjf.wanandroid.vh.FooterViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public abstract class BaseListFragment<E, P extends BaseListPresenter> extends BaseFragment implements IListMvpView<List<E>>, BaseAdapter.OnAdapterErrorListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    protected P mPresenter;
    protected BaseAdapter<E> mAdapter;
    protected LoadingMoreScrollListenerM mLoadingMoreScrollListener;

    public abstract P providePresenter();

    protected abstract BaseAdapter<E> provideAdapter();

    @Override
    public int provideLayoutId() {
        return R.layout.item_list;
    }

    @Override
    final protected void initPresenter() {
        mPresenter = providePresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void initOnCreateView() {
        mAdapter = provideAdapter();
        mAdapter.setErrorListener(this);
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        mLoadingMoreScrollListener = new LoadingMoreScrollListenerM(mPresenter);
        recycle_view.addOnScrollListener(mLoadingMoreScrollListener);
        recycle_view.setAdapter(mAdapter);
        mPresenter.start();
    }

    @Override
    public void showContent(List<E> data, boolean refresh) {
        if (refresh) {
            mAdapter.setmList(data);
        } else {
            mAdapter.addList(data);
        }
        mAdapter.bindFooter(CommonUtil.isEmpty(data) ? FooterViewHolder.NO_MORE : FooterViewHolder.HAS_MORE);
        mLoadingMoreScrollListener.loadingFinish();
    }

    @Override
    public void showErrorPage(String message, boolean refresh) {
        if (mAdapter == null) {
            return;
        }
        if (refresh) {
            mAdapter.onShowError(message);
        } else {
            mAdapter.bindFooter(FooterViewHolder.ERROR);
        }
        mLoadingMoreScrollListener.loadingFinish();
    }

    @Override
    public void showEmptyPage(String emptyInfo) {
        if (mAdapter == null) {
            return;
        }
        mAdapter.onShowEmpty(TextUtils.isEmpty(emptyInfo) ? Constant.RESPONSE_EMPTY : emptyInfo);
        mLoadingMoreScrollListener.loadingFinish();
    }

    @Override
    public void onDestroyView() {
        if (mAdapter != null) {
            recycle_view.setAdapter(null);
        }
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
