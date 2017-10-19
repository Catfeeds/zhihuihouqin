package com.moe.wl.ui.main.model;

import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface PreOderBarberModel extends MvpModel{

    Observable getData(int id);
    Observable createOrder(Order order, List<Itemid> list);
}
