package com.moe.wl.ui.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by 我的电脑 on 2017/9/29 0029.
 */

public class NoScrollExpandableListView extends ExpandableListView {
    public NoScrollExpandableListView(Context context) {
        super(context,null);
    }

    public NoScrollExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs,-1);
    }

    public NoScrollExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
