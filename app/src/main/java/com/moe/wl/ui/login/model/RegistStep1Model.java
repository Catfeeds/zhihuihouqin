package com.moe.wl.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep1Model extends MvpModel {
    Observable getData();

    //获取验证码
    Observable getCaptcha(String s, int s1);

    /*//检测验证码
    Observable checkCode(String s, String s1);*/
    Observable bindPhone(String loginType, String userName, String thirdNum,
                         String isRegister, String password, String captcha);
}
