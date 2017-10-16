package com.moe.wl.ui.home.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.SubscribeInfoModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class SubscribeInfoModelImpl implements SubscribeInfoModel {

    @Override
    public Observable subscribeInfo() {
        Observable observable = RetrofitUtils.getInstance().subscribeInfo();
        return observable;
    }
}
