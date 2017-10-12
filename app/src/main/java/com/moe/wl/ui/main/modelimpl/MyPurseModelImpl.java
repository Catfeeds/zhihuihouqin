package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MyPurseModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MyPurseModelImpl implements MyPurseModel {

    @Override
    public Observable getInfo() {
        Observable observable = RetrofitUtils.getInstance().findUserWallet();
        return observable;
    }
}
