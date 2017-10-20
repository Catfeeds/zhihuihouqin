package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.BannerModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class BannerModelImpl implements BannerModel {

    @Override
    public Observable getBanner(int serviceType) {
        Observable observable = RetrofitUtils.getInstance().getBanner(serviceType);
        return observable;
    }
}
