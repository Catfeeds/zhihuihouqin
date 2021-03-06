package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.SpDetailModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpDetailModelImpl implements SpDetailModel {

    @Override
    public Observable getData(String s) {
        Observable observable = RetrofitUtils.getInstance().getSpDetail(s);
        return observable;
    }

    @Override
    public Observable collect(int s, int s1) {
        Observable observable = RetrofitUtils.getInstance().getHealthInfoColect(s,s1);
        return observable;

    }

    @Override
    public Observable getShopCarInfo(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpShopCarInfo(id);
        return observable;
    }

    @Override
    public Observable shopCar(String s, String count) {
        Observable observable = RetrofitUtils.getInstance().shopCar(s,count);
        return observable;
    }
}
