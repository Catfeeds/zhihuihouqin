package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.StatisticsModel;

import rx.Observable;


public class StatisticsModelImpl implements StatisticsModel {

    @Override
    public Observable getData(String time,int i,int j,int k) {
        Observable observable = RetrofitUtils.getInstance().energyCostQuery(time,i,j,k);
        return observable;
    }

}
