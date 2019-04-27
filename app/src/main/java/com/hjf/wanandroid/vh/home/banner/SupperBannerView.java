package com.hjf.wanandroid.vh.home.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjf.wanandroid.R;
import com.hjf.wanandroid.been.BannerInfo;
import com.hjf.wanandroid.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


/**
 * 轮播图，一个LoopView的示例实现，LoopView将Banner循环体和指示器解耦，便于复用和扩展
 * Created by hzw on 2019/1/22.
 */
public class SupperBannerView extends FrameLayout implements LoopView.LoopListener<SupperBannerView.Holder> {

    private LinearLayout indicatorGroup;
    private List<BannerInfo.DataBean> datas;
    private LoopView loopView;
    private boolean onePageScroll;
    private float indicatorHeight;
    private float indicatorSpace;
    private float indicatorWidth;
    private int selectIndicatorColor;
    private int indicatorColor;
    private int showDuration;

    public SupperBannerView(Context context) {
        this(context, null);
        init(null, 0);
    }

    public SupperBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SupperBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SupperBannerView, defStyleAttr, 0);
        indicatorColor = array.getColor(R.styleable.SupperBannerView_indicatorColor, Color.GRAY);
        selectIndicatorColor = array.getColor(R.styleable.SupperBannerView_selectIndicatorColor, Color.RED);
        indicatorSpace = array.getDimension(R.styleable.SupperBannerView_indicatorSpace, UiUtils.dip2px(2));
        indicatorWidth = array.getDimension(R.styleable.SupperBannerView_indicatorWidth, UiUtils.dip2px(21));
        indicatorHeight = array.getDimension(R.styleable.SupperBannerView_indicatorHeight, UiUtils.dip2px(7));
        showDuration = array.getInteger(R.styleable.SupperBannerView_showDuration, 3000);
        onePageScroll = array.getBoolean(R.styleable.SupperBannerView_onPageScroll, true);
        array.recycle();

        //布局
        loopView = new LoopView(getContext());
        this.addView(loopView);
        LayoutParams paramsIndicator = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsIndicator.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        paramsIndicator.bottomMargin = (int) UiUtils.dip2px(15);
        indicatorGroup = new LinearLayout(getContext());
        this.addView(indicatorGroup, paramsIndicator);
        datas = new ArrayList<>();
    }


    public void setData(List<BannerInfo.DataBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        datas.clear();
        datas.addAll(list);
        indicatorGroup.removeAllViews();
        loopView.setLoopData(list.size(), showDuration, onePageScroll, this);
    }

    @NonNull
    @Override
    public Holder onCreateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.vh_home_banner_item, container, false);
        return new Holder(view);
    }

    @Override
    public void onBindItem(Holder holder, int position) {
        holder.title.setText(datas.get(position).getTitle());
        Glide.with(getContext())
                .load(datas.get(position).getImagePath())
                .into(holder.imageView);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        indicatorGroup.requestLayout();
        indicatorGroup.invalidate();
    }

    @Override
    public void changeListener(int position, float offset) {
    }

    @Override
    public void itemClick(View view, int position) {
        Toast.makeText(getContext(), "high起来", Toast.LENGTH_SHORT).show();
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