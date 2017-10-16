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

}
