package com.moe.wl.ui.home.modelimpl.saving;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.saving.RankingModel;

import rx.Observable;


public class RankingModelImpl implements RankingModel {

    @Override
    public Observable information() {
        Observable observable = RetrofitUtils.getInstance().information();
        return observable;
    }

}
