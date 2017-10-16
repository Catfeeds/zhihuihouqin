package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.ChargeOrderBean;
import com.moe.wl.ui.main.bean.FindRemainBean;
import com.moe.wl.ui.main.bean.LastCardNumBean;
import com.moe.wl.ui.main.bean.WeixinBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface MealsRechargeView extends MvpView {

    void getfindLastCardNumResult(LastCardNumBean bean);
    void getFindRemain(FindRemainBean bean);
    void generateChargeOrder(ChargeOrderBean bean);
    void aliPay(AlipayBean bean);
    void weiXinPay(WeixinBean bean);

}
