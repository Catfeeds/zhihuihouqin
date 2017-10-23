package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ConfirmVegetableOrderModel;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ConfirmVegetableOrderModelImpl implements ConfirmVegetableOrderModel {

    @Override
    public Observable submitVegetableOrder(HashMap<String, Object> map) {
        Observable observer = RetrofitUtils.getInstance().submitVegetableOrder(map);
        return observer ;
    }
}
