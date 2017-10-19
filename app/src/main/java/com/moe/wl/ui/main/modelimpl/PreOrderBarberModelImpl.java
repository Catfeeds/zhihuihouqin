package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;
import com.moe.wl.ui.main.model.PreOderBarberModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import java.util.List;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PreOrderBarberModelImpl implements PreOderBarberModel {

    @Override
    public Observable getData(int id) {
        LogUtil.log("PreOrderBarberModelImpl请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().preOrderBarber(id);
        return observer ;
    }
    @Override
    public Observable createOrder(Order order, List<Itemid> list) {
        Observable observable = RetrofitUtils.getInstance().createOrder(order,list);
        return observable;
    }
}
