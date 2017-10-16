package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.RechargeAmountModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeAmountModelImpl implements RechargeAmountModel {

    @Override
    public Observable getData(double money, int paytype, int ordertype) {
        Observable observable = RetrofitUtils.getInstance().generateChargeWalletOrder(money,paytype,ordertype);
        return observable;
    }
}