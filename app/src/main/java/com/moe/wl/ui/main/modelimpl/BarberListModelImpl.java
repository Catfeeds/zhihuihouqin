package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.BarberListModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberListModelImpl implements BarberListModel {

    @Override
    public Observable getData() {
        LogUtil.log("BarberListModelImpl请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getBarberList();
        return observer ;
    }
}
