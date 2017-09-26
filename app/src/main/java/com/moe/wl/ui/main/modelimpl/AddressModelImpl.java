package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddressModelImpl implements AddressModel {

    @Override
    public Observable getAddress() {
        Observable observable = RetrofitUtils.getInstance().getAddress();
        return observable;
    }
}
