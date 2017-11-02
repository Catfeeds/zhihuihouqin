package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.RechargeOrderModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeOrderModelImpl implements RechargeOrderModel {
    @Override
    public Observable getOrder(int page,int limit,int type) {
        Observable observable = RetrofitUtils.getInstance().getChargeOrder(page,limit,type);
        return observable;
    }
}
