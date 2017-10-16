package com.moe.wl.ui.home.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.OfficeListModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class OfficeListModelImpl implements OfficeListModel {

    @Override
    public Observable officelist() {
        Observable observable = RetrofitUtils.getInstance().officelist();
        return observable;
    }

}
