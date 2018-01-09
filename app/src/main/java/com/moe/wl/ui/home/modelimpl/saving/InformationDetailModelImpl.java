package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.InformationDetailModel;
import com.moe.wl.ui.home.model.saving.InformationModel;

import rx.Observable;


public class InformationDetailModelImpl implements InformationDetailModel {

    @Override
    public Observable informationDetail(int id) {
        Observable observable = RetrofitUtils.getInstance().informationDetail(id);
        return observable;
    }
}
