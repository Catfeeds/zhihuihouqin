package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.RechargeModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeModelImpl implements RechargeModel {

    @Override
    public Observable getRechargeData(String page,String limit) {
        Observable observable = RetrofitUtils.getInstance().findChargeOrder(page,limit);
        return observable;
    }
}
