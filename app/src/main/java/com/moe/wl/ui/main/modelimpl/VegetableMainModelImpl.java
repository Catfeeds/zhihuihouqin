package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.VegetableMainModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class VegetableMainModelImpl implements VegetableMainModel {

    @Override
    public Observable getVegetableData(int page, String keyword) {
        Observable observable = RetrofitUtils.getInstance().getVegetableData(page, keyword);
        return observable;
    }

    @Override
    public Observable canOrdered() {
        Observable observable = RetrofitUtils.getInstance().canOrdered();
        return observable;
    }
}
