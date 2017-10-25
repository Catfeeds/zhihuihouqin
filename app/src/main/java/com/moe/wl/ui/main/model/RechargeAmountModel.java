package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface RechargeAmountModel extends MvpModel {

    Observable getData(double money,int ordertype);
    Observable pay(String orderid,String orderCode,String orderType,int paytype);


}
