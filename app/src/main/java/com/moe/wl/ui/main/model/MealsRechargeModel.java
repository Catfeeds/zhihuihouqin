package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface MealsRechargeModel extends MvpModel {

    Observable findLastCardNum();
    Observable findRemain();
    Observable generateChargeOrder(String money,int paytype,String cardNum);
    Observable pay(String orderid,String ordercode,String ordertype,int paytype);

}
