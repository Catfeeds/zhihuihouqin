package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.OrderingModel;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class OrderingModelImpl implements OrderingModel {
    @Override
    public Observable getData(String phoneNumber, int count, int type, String fixedmealtype, int duration) {
        LogUtil.log("OrderingModelImpl-->getData");
        Observable observer = RetrofitUtils.getInstance().createOrdering(phoneNumber, count, type, fixedmealtype, duration);
        return observer;
    }

    @Override
    public Observable getTime() {
        LogUtil.log("OrderingModelImpl-->getTime");
        Observable observer = RetrofitUtils.getInstance().getTime();
        return observer;
    }
}
