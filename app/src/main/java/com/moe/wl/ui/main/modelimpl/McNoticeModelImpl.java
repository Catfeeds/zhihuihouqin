package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.McNocticeModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticeModelImpl implements McNocticeModel {

    @Override
    public Observable getData(int type) {
        Observable observable = RetrofitUtils.getInstance().addFavor(type);
        return observable;
    }
}
