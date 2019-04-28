package com.hjf.wanandroid.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.hjf.wanandroid.R;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public class RatioFrameLayout extends FrameLayout {
    /**
     * 默认的宽高比,用于宽高都是wrap_content时,以宽为准,宽高相同
     * <p>
     * height = width * scale
     */
    private static final float DEFAULT_SCALE = 1F;
    private float mDividerScale = DEFAULT_SCALE;
    private float mMultiplyRatio = 0;
    private static final float RATIO_BIG = 2.13F;

    public RatioFrameLayout(Context context) {
        this(context, null);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioByWidthView);
        mDividerScale = typedArray.getFloat(R.styleable.RatioByWidthView_ratio, RATIO_BIG);
        mMultiplyRatio = typedArray.getFloat(R.styleable.RatioByWidthView_multiplyRatio, 0);

        int imageType = typedArray.getInt(R.styleable.RatioByWidthView_imageType, -1);
        switch (imageType) {
            case 0: // origin
                mDividerScale = 1.0F;
                break;
            case 1:// big
                mDividerScale = RATIO_BIG;
                break;
        }

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);

        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);

        int width_result;
        int height_result;

        width_result = size_width;

        if (mode_height == MeasureSpec.EXACTLY) {
            height_result = size_height;
        } else {
            if (mMultiplyRatio != 0) {
                height_result = (int) (width_result * mMultiplyRatio + 0.5);
            } else {
                height_result = (int) (width_result / mDividerScale + 0.5);
            }
        }


        int measureSpecWidth = MeasureSpec.makeMeasureSpec(width_result, MeasureSpec.EXACTLY);
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(height_result, MeasureSpec.EXACTLY);

        super.onMeasure(measureSpecWidth, measureSpecHeight);
    }
}

