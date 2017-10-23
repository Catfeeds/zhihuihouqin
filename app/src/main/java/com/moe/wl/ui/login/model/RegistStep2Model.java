package com.moe.wl.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep2Model extends MvpModel{
    Observable getData();

    Observable regist(String s, String code, String pas);

    Observable changePassWord(String s, String code, String pas);

    Observable bindPhone(String loginType, String userName, String thirdNum,
                         String isRegister, String password, String captcha);

}
