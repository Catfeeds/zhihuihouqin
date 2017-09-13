package cn.lc.model.ui.mywidget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */

public class NoScrollLinearLayoutManager extends LinearLayoutManager {

    private boolean isScrollEnabled;

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }

}
