package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ExpertOrderModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public class ExpertOrderModelImpl implements ExpertOrderModel {

    @Override
    public Observable submitExpertOrder(int doctorId, int timeId) {
        Observable observable = RetrofitUtils.submitExpertOrder(doctorId, timeId);
        return observable;
    }
}
