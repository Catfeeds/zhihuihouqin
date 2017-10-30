package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.SpPayModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpPayModelImpl implements SpPayModel {

    @Override
    public Observable getData(String type) {
        Observable observable = RetrofitUtils.getInstance().findUserWallet1(type);
        return observable;
    }

    @Override
    public Observable walletpay(String orderid, String orderCode, String orderType, int paytype, String paypass,int count) {
        Observable observable = RetrofitUtils.getInstance().walletPay(orderid,orderCode,orderType,paytype,paypass,count);
        return observable;
    }
    @Override
    public Observable getData() {
        Observable observable = RetrofitUtils.getInstance().hasPaypass();
        return observable;
    }
}
