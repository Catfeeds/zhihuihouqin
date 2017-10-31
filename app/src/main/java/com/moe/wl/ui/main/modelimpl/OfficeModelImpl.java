package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.OfficeNumModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeModelImpl implements OfficeNumModel {

    @Override
    public Observable getOfficeList(int departid) {
        Observable observable = RetrofitUtils.getInstance().getofficelist(departid);
        return observable;
    }
}
