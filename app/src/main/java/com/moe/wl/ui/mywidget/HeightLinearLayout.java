package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.moe.wl.framework.utils.LogUtils;

/**
 * 类描述：
 * 作者：Shixhe On 2017/11/8 0008
 */

public class HeightLinearLayout extends LinearLayout {

    public HeightLinearLayout(Context context) {
        super(context);
    }

    public HeightLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    int height;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            height += getChildAt(i).getHeight();
        }
        LogUtils.d("height : " + height);
        if (listener != null) {
            listener.onHeight(height);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private OnLinearHeight listener;

    public void setOnLinearHeight(OnLinearHeight listener) {
        this.listener = listener;
    }

    public interface OnLinearHeight {
        void onHeight(int height);
    }

}
