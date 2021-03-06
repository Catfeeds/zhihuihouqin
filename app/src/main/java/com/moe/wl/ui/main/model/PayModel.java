package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface PayModel extends MvpModel {

    Observable pay(String orderid,String orderCode,String orderType,int paytype);
    Observable getfindUserWallet();
    Observable walletpay(String orderid,String orderCode,String orderType,int paytype,String paypass,int count);
    Observable getData();//是否有支付密码

}
