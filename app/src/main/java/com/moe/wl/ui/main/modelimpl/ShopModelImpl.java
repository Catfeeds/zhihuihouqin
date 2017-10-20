package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ShopModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ShopModelImpl implements ShopModel {

    @Override
    public Observable getData() {
        Log.e("ShopModel请求数据-->","---");
        Observable observer = RetrofitUtils.getInstance().getShopInfo();
        return observer;
    }

    @Override
    public Observable getServiceInfo(int serviceType) {
        Log.e("ShopModel请求数据-->","---");
        Observable observer = RetrofitUtils.getInstance().getBanner(serviceType);
        return observer;
    }
}
