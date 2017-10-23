package com.moe.wl.ui.login.view;

import com.moe.wl.ui.home.bean.LoginBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface LoginView extends MvpView{

    void showToast();
    void loginSuccess(LoginBean loginBean);

    void binding(String loginType,String thirdNum);

}
