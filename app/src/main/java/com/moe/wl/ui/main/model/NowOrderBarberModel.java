package com.moe.wl.ui.main.model;

import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface NowOrderBarberModel extends MvpModel {

    Observable orderBarber();
    Observable createOrder(Order order, List<Itemid> list);

}
