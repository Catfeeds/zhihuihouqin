package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.PublicAcountModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PublicAcountModelImpl implements PublicAcountModel {

    @Override
    public Observable findList() {
        Observable observable = RetrofitUtils.getInstance().findPurchaseAccountList();
        return observable;
    }
}
