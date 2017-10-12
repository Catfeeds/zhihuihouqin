package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.SignUpPersonModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SignUpPersonModelImpl implements SignUpPersonModel {

    @Override
    public Observable getData(int id) {
        Observable observable = RetrofitUtils.getInstance().getActivityUserDetail(id);
        return observable;
    }
}
