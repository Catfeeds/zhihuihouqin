package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.BarberDetailModel;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberDetailModelImpl implements BarberDetailModel {

    @Override
    public Observable getData(int id) {
        LogUtil.log("BarberDetailModelImpl-->login");
        Observable observer = RetrofitUtils.getInstance().getBarberDetail(id);
        return observer ;
    }
}
