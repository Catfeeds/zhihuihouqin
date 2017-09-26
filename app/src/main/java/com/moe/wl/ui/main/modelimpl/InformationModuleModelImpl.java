package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.InformationModuleModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationModuleModelImpl implements InformationModuleModel {

    @Override
    public Observable getInformationClass(int user) {
        Observable observable = RetrofitUtils.getInstance().getInformationClass(user);
        return observable;
    }

    @Override
    public Observable updataInformationClass(int[] ids) {
        Observable observable = RetrofitUtils.getInstance().editMyInformationClass(ids);
        return observable;
    }
}
