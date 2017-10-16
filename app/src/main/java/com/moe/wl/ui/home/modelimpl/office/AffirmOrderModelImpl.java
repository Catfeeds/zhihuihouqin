package com.moe.wl.ui.home.modelimpl.office;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.office.AffirmOrderModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class AffirmOrderModelImpl implements AffirmOrderModel {

    @Override
    public Observable orderinfo() {
        Observable observable = RetrofitUtils.getInstance().orderinfo();
        return observable;
    }

}
