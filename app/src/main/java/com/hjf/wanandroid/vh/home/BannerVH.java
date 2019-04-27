package com.hjf.wanandroid.vh.home;

import android.view.View;
import android.view.ViewGroup;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.been.BannerInfo;
import com.hjf.wanandroid.been.WanAndroidInfo;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.home.banner.SupperBannerView;

import butterknife.BindView;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class BannerVH extends BaseViewHolder<WanAndroidInfo> {

    @BindView(R.id.sbv_home_banner)
    SupperBannerView sbv_home_banner;

    public BannerVH(ViewGroup parent, View.OnClickListener listener) {
        super(R.layout.vh_home_banner, parent, listener);
    }

    @Override
    public void bind(WanAndroidInfo data) {
        Object object = data.getObject();
        if (object instanceof BannerInfo) {
            BannerInfo dataBean = (BannerInfo) object;
            sbv_home_banner.setData(dataBean.getData());
        }
    }
}
