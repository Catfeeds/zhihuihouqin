package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.HasPwdModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HasPwdModelImpl implements HasPwdModel {
    @Override
    public Observable getData() {
        Observable observable = RetrofitUtils.getInstance().hasPaypass();
        return observable;
    }
}
