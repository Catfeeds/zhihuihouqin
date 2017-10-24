package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.UserDepositBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface MyDepositView extends MvpView {

    void getUserDepositResult(UserDepositBean bean);

    void getOrderResult(WalletOrderBean bean);
    void backDepositResult(ActivityPostBean bean );

    void getTimeSucc(OrderWaterTimeBean bean);

    void generateOrderSucc(GenerateOrderWaterBean bean);
}
