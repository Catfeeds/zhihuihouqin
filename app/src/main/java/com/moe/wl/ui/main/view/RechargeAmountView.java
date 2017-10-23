package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.bean.WeixinBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface RechargeAmountView extends MvpView {

    void rechargeResult(WalletOrderBean bean);
    void aliPay(AlipayBean bean);
    void weiXinPay(WeixinBean bean);

}
