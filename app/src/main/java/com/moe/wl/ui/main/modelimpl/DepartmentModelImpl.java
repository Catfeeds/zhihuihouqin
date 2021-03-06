package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.DepartMentModel;
import com.moe.wl.ui.main.model.NationsModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class DepartmentModelImpl implements DepartMentModel {

    @Override
    public Observable getDepartList() {
        Observable observable = RetrofitUtils.getInstance().getdepartList();
        return observable;
    }
}
