package com.moe.wl.ui.login.view;

import com.moe.wl.framework.widget.bean.BindPhoneBean;
import com.moe.wl.ui.login.bean.RegistBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep2View extends MvpView{

   // void showToast();
    void registSuccess(RegistBean registBean);

    void changePassWord(RegistBean registBean);

    void bindSuccess(BindPhoneBean registBean);
}
