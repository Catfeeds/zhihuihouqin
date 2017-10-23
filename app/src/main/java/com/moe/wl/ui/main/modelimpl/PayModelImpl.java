package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.PayModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PayModelImpl implements PayModel {

    @Override
    public Observable pay(String orderid,String orderCode, String orderType, int paytype) {
        Observable observable = RetrofitUtils.getInstance().pay(orderid,orderCode,orderType,paytype);
        return observable;
    }

    @Override
    public Observable walletpay(String orderid, String orderCode, String orderType, int paytype, String paypass,int count) {
        Observable observable = RetrofitUtils.getInstance().walletPay(orderid,orderCode,orderType,paytype,paypass,count);
        return observable;
    }

    @Override
    public Observable getfindUserWallet() {
        Observable observable = RetrofitUtils.getInstance().findUserWallet();
        return observable;
    }
}
