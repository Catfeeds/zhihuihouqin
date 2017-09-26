package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.MoreServiceModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/22 0022
 */

public class MoreServiceModelImpl implements MoreServiceModel {

    @Override
    public Observable getMyService() {
        Observable observable = RetrofitUtils.getMyService();
        return observable;
    }

    @Override
    public Observable submitMyService(int[] ids) {
        Observable observable = RetrofitUtils.submitMyService(ids);
        return observable;
    }
}
