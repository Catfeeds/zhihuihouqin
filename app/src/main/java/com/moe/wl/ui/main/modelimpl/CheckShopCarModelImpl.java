package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.CheckShopCarModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class CheckShopCarModelImpl implements CheckShopCarModel {

    @Override
    public Observable checkShopCar() {
        Observable observable = RetrofitUtils.getInstance().checkShopCar();
        return observable;
    }

    @Override
    public Observable cancelItem(int[] arr) {
        Observable observable = RetrofitUtils.getInstance().cancelItem(arr);
        return observable;
    }
}
