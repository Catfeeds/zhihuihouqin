package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.CancelOrderingModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class CancelOrderingModelImpl implements CancelOrderingModel {
    @Override
    public Observable getReasonList() {
        Log.e("CancelOrderingModelImpl", "请求数据-->ReasonList");
        Observable observer = RetrofitUtils.getInstance().getReason();
        return observer;
    }

    @Override
    public Observable cancelOrder(int oid, int[] reasonIds, String reasonContent) {
        Log.e("CancelOrderingModelImpl", "请求数据-->ReasonList");
        Observable observer = RetrofitUtils.getInstance().cancelOrder(oid, reasonIds, reasonContent);
        return observer;
    }
}
