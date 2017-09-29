package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.HairStyleDetailModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HairStyleDetailModelImpl implements HairStyleDetailModel {

    @Override
    public Observable getData(int workid) {
        Observable observable = RetrofitUtils.getInstance().getBarberProductDetail(workid);
        return observable;
    }

    @Override
    public Observable collect(int type, int i) {
        Observable observable = RetrofitUtils.getInstance().getHealthInfoColect(type,i);
        return observable;
    }
}
