package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.MyDepositModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MyDepositModelImpl implements MyDepositModel {

    @Override
    public Observable getDepositInfo() {
        Observable observable = RetrofitUtils.getInstance().getUserDeposit();
        return observable;
    }

    @Override
    public Observable generateChargeWalletOrder(double s, int s1, int s2) {
        Observable observable = RetrofitUtils.getInstance().generateChargeWalletOrder(s,s2);
        return observable;
    }


    @Override
    public Observable getOrderTime() {
        Observable observable = RetrofitUtils.getInstance().getOrderWaterTime();
        return observable;
    }

    @Override
    public Observable generateOrder(String realname, String mobile, int addressId, String sendTime, Object[] arr, String remark) {
        Observable observable = RetrofitUtils.getInstance().generateOrder(realname,mobile,addressId,sendTime,arr,remark);
        return observable;
    }

    @Override
    public Observable backDeposit() {
        Observable observable = RetrofitUtils.getInstance().backDeposit();
        return observable;
    }
}
