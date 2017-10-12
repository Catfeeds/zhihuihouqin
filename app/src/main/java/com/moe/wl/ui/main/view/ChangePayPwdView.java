package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ChangePayPwdView extends MvpView {

    void modifyCodeResult(ActivityPostBean bean);
    void checkOldPasswordResult(ActivityPostBean bean);

}
