package com.moe.wl.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface LoginModel extends MvpModel{
    Observable getData();

    Observable login(String s, String s1);

    Observable thirdLogin(String thirdNum, String loginType);
}
