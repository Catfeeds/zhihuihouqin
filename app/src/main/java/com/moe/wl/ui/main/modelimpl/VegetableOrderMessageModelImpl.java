package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.VegetableOrderMessageModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class VegetableOrderMessageModelImpl implements VegetableOrderMessageModel {

    @Override
    public Observable getVegetableSelectTime() {
        Observable observer = RetrofitUtils.getInstance().getVegetableSelectTime();
        return observer ;
    }
}
