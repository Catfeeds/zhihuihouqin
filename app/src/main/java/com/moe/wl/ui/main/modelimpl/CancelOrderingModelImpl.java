package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.CancelOrderingModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class CancelOrderingModelImpl implements CancelOrderingModel {

    // 获取取消订单原因
    @Override
    public Observable getCancelReason(int type) {
        Observable observer = RetrofitUtils.getInstance().getCancelReason(type);
        return observer;
    }

    // 取消报修订单
    @Override
    public Observable cancelOrder(int serviceType, int oid, String content) {
        Observable observer = RetrofitUtils.getInstance().cancelOrder(serviceType, oid, content);
        return observer;
    }
}
