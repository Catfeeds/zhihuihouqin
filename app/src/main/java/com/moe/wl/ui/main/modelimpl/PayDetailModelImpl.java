package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.PayDetailModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PayDetailModelImpl implements PayDetailModel {

    @Override
    public Observable getData(String page, String limit) {
        Observable observable = RetrofitUtils.getInstance().findWalletLog(page,limit);
        return observable;
    }
}
