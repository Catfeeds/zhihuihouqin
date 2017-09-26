package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.OrderHomeModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderHomeModelImpl implements OrderHomeModel {

    @Override
    public Observable getWaterType() {
        Observable observable = RetrofitUtils.getInstance().queryWaterType();
        return observable;
    }
}
