package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface CancelOrderingModel extends MvpModel {

    // 获取取消订单原因
    Observable getCancelReason(int type);

    // 取消报修订单
    Observable cancelOrder(int serviceType, int oid, String content);

}
