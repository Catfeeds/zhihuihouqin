package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.NowOrderBarberModel;

import java.util.List;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class NowOrderBarberModelImpl implements NowOrderBarberModel {

    @Override
    public Observable orderBarber() {
        Observable observable = RetrofitUtils.getInstance().nowOrderBarber();
        return observable;
    }

    @Override
    public Observable createOrder(Order order, List<Itemid> list) {
        Observable observable = RetrofitUtils.getInstance().createOrder(order,list);
        return observable;
    }
}
