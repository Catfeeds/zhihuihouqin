package com.moe.wl.ui.home.modelimpl.office;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.office.OfficeDetailsModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class OfficeDetailsModelImpl implements OfficeDetailsModel {

    @Override
    public Observable officedetails(String id) {
        Observable observable = RetrofitUtils.getInstance().officedetails(id);
        return observable;
    }

}
