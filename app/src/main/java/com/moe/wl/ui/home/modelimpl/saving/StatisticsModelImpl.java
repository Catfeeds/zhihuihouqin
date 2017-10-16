package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.StatisticsModel;

import rx.Observable;


public class StatisticsModelImpl implements StatisticsModel {

    @Override
    public Observable information() {
        Observable observable = RetrofitUtils.getInstance().information();
        return observable;
    }

}
