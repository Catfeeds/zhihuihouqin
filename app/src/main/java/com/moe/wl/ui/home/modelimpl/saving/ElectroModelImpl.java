package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.ElectroModel;
import com.moe.wl.ui.home.model.saving.InformationDetailModel;

import rx.Observable;


public class ElectroModelImpl implements ElectroModel {

    @Override
    public Observable getInfo(String time, int dateType, int energyType, int bid) {
        Observable observable = RetrofitUtils.getInstance().energyCostQuery(time,dateType,energyType,bid);
        return observable;
    }
}
