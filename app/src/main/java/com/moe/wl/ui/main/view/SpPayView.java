package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.UserWalletBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface SpPayView extends MvpView {

    void getResult(UserWalletBean bean);
    void personalWallet(ActivityPostBean bean);
    void isHasPwd(ActivityPostBean bean);

}
