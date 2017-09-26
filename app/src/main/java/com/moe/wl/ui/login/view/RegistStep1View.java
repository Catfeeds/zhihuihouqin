package com.moe.wl.ui.login.view;

import com.moe.wl.framework.widget.bean.BindPhoneBean;
import com.moe.wl.ui.login.bean.CaptchaBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep1View extends MvpView{


    void success(CaptchaBean captchaBean);
    void bindResult(BindPhoneBean bindPhoneBean);
}
