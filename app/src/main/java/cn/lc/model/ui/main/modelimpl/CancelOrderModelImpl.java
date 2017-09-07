package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.CancelOrderModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class CancelOrderModelImpl implements CancelOrderModel {
    @Override
    public Observable getReasonList() {
        Log.e("CancelOrderModelImpl", "请求数据-->ReasonList");
        Observable observer = RetrofitUtils.getInstance().getReason();
        return observer;
    }

    @Override
    public Observable cancelOrder(int oid, int[] reasonIds, String reasonContent) {
        Log.e("CancelOrderModelImpl", "请求数据-->ReasonList");
        Observable observer = RetrofitUtils.getInstance().cancelOrder(oid, reasonIds, reasonContent);
        return observer;
    }
}
