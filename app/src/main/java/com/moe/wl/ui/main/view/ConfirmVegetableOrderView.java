package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.PayBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */

public interface ConfirmVegetableOrderView extends MvpView {

    void submitVegetableOrderSucc(PayBean bean);

}