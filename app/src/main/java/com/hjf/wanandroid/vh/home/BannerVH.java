package com.hjf.wanandroid.vh.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjf.wanandroid.R;
import com.hjf.wanandroid.app.WanApplication;
import com.hjf.wanandroid.base.widget.RatioFrameLayout;
import com.hjf.wanandroid.been.BannerInfo;
import com.hjf.wanandroid.utils.CommonUtil;
import com.hjf.wanandroid.utils.ImageLoaderUtils;
import com.hjf.wanandroid.utils.ScreenUtil;
import com.hjf.wanandroid.utils.ToastUtils;
import com.hjf.wanandroid.vh.BaseViewHolder;
import com.hjf.wanandroid.vh.home.banner.LoopView;

import java.util.List;

import butterknife.BindView;

/**
 * @author Jianfeng He
 * @email hjfstory@foxmail.com
 * @date 2019-04-27
 */
public class BannerVH extends BaseViewHolder<List<BannerInfo.DataBean>> implements LoopView.LoopListener<BannerVH.Holder> {

    @BindView(R.id.loop_container)
    RatioFrameLayout loop_container;
    @BindView(R.id.sbv_home_banner)
    LoopView sbv_home_banner;

    private static final int DURATION = 4000;
    private List<BannerInfo.DataBean> datas;

    public BannerVH(ViewGroup parent, View.OnClickListener listener) {
        super(R.layout.vh_home_banner, parent, listener);
    }

    @Override
    public void bind(List<BannerInfo.DataBean> data, int position) {
        if (CommonUtil.isEmpty(data)) return;
        datas = data;
        sbv_home_banner.setLoopData(data.size(), DURATION, false, this);
    }

    public void setLoopVpViewHeight(float whPercent) {
        if (itemView == null || whPercent == 0) return;
        ViewGroup.LayoutParams params = loop_container.getLayoutParams();
        params.width = ScreenUtil.getScreenWidth() - itemView.getPaddingLeft() - itemView.getPaddingRight();
        params.height = (int) (params.width / whPercent);
        loop_container.setLayoutParams(params);
    }

    @NonNull
    @Override
    public BannerVH.Holder onCreateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.vh_home_banner_item, container, false);
        return new Holder(view);
    }

    @Override
    public void onBindItem(BannerVH.Holder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        ImageLoaderUtils.instance().disImage(WanApplication.getContext(), datas.get(position).getImagePath(), holder.imageView);
        holder.imageView.setTag(R.id.item_banner, datas.get(position).getUrl());
        holder.imageView.setOnClickListener(mListener);
    }

    @Override
    public void changeListener(int position, float offset) {

    }

    @Override
    public void selectListener(int position) {
    }

    static class Holder extends LoopView.LoopHolder {

        private ImageView imageView;
        private TextView title;

        Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_home_banner_img);
            title = itemView.findViewById(R.id.tv_home_banner_title);
        }
    }
}
