package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.ComparisonModel;

import rx.Observable;


public class ComparisonModelImpl implements ComparisonModel {

    @Override
    public Observable information() {
        Observable observable = RetrofitUtils.getInstance().information();
        return observable;
    }

    @Override
    public Observable getBuildNum() {
        Observable observable = RetrofitUtils.getInstance().getBuildNum();
        return observable;
    }

    @Override
    public Observable getinfo(int dateType, int energyType, int[] bid, String date) {
        Observable observable = RetrofitUtils.getInstance().energyCostCompare(dateType,energyType,bid,date);
        return observable;
    }

}
