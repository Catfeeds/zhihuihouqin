package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.AddAddressModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddAddressModelImpl implements AddAddressModel {

    @Override
    public Observable addAddress(String realname, String mobile, String address) {
        Observable observable = RetrofitUtils.getInstance().addAddress(realname, mobile, address);
        return observable;
    }

    @Override
    public Observable editAddress(int id, String realname, String mobile, String address) {
        Observable observable = RetrofitUtils.getInstance().editAddress(id, realname, mobile, address);
        return observable;
    }

    @Override
    public Observable deleteAddress(int[] id) {
        Observable observable = RetrofitUtils.getInstance().deleteAddress(id);
        return observable;
    }
}
