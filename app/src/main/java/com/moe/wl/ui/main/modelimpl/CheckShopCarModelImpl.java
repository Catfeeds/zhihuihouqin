package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.CheckShopCarModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class CheckShopCarModelImpl implements CheckShopCarModel {

    @Override//查询购物车
    public Observable checkShopCar() {
        Observable observable = RetrofitUtils.getInstance().checkShopCar();
        return observable;
    }

    @Override//删除购物车里的商品项
    public Observable cancelItem(int[] arr) {
        Observable observable = RetrofitUtils.getInstance().cancelItem(arr);
        return observable;
    }
}
