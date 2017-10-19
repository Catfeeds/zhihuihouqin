package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MealsRechargeModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MealsRechargeModelImpl implements MealsRechargeModel {

    @Override
    public Observable findLastCardNum() {
        Observable observable = RetrofitUtils.getInstance().findLastCardNum();
        return observable;
    }

    @Override
    public Observable findRemain() {
        Observable observable = RetrofitUtils.getInstance().findRemain();
        return observable;
    }

    @Override
    public Observable generateChargeOrder(String money, int paytype, String cardNum) {
        Observable observable = RetrofitUtils.getInstance().generateChargeOrder(money,paytype,cardNum);
        return observable;
    }

    @Override
    public Observable pay(String orderid,String ordercode, String ordertype, int paytype) {
        Observable observable = RetrofitUtils.getInstance().pay(orderid,ordercode,ordertype,paytype);
        return observable;
    }
}
