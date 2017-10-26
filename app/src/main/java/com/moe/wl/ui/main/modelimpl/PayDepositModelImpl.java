package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.PayDepositModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */
public class PayDepositModelImpl implements PayDepositModel {

    @Override
    public Observable getOrder(double i,int j) {
        Observable observable = RetrofitUtils.getInstance().generateChargeWalletOrder(i,j);
        return observable;
    }
}
