package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.moe.wl.framework.utils.LogUtils;

/**
 * 类描述：判断是否滚动到最下方
 * 作者：Shixhe On 2017/11/3 0003
 */

public class ScrollViewBottom extends ScrollView {

    public ScrollViewBottom(Context context) {
        super(context);
    }

    public ScrollViewBottom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = getChildAt(getChildCount() - 1);
        int d = view.getBottom();
        d -= (getHeight() + getScrollY());
        if (d == 0) {
            if (listener != null) {
                listener.onScrollBottom(true);
            }
        } else {
            super.onScrollChanged(l, t, oldl, oldt);
            if (listener != null) {
                listener.onScrollBottom(false);
            }
        }
    }

    private OnScrollBottomListener listener;

    public void setONScrollBottomListener(OnScrollBottomListener listener) {
        this.listener = listener;
    }

    public interface OnScrollBottomListener {
        void onScrollBottom(boolean isBottom);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        LogUtils.d("scrollX : " + scrollX + "  scrollY : " + scrollY + "  clampedX : " + clampedX + "  clampedY : " + clampedY);
    }
}
