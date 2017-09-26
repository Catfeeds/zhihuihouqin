package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.InformationClassModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationClassModelImpl implements InformationClassModel {

    @Override
    public Observable getUserInformationClass(int user) {
        Observable observable = RetrofitUtils.getInstance().getInformationClass(user);
        return observable;
    }
}
