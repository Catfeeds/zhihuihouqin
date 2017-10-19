package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.CarTypeModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class CarTypeModelImpl implements CarTypeModel {

    @Override
    public Observable getcartypelist() {
        Observable observable = RetrofitUtils.getInstance().getcartypelist();
        return observable;
    }
}
