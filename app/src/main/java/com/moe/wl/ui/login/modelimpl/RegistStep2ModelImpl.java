package com.moe.wl.ui.login.modelimpl;

import com.moe.wl.ui.login.model.RegistStep2Model;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2ModelImpl implements RegistStep2Model {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer;
    }

    @Override
    public Observable regist(String s, String code, String pas) {
        LogUtil.log("RegistStep2Model请求数据-->regist");
        Observable observer = RetrofitUtils.getInstance().register(s, code, pas);
        return observer;

    }

    @Override
    public Observable changePassWord(String s, String code, String pas) {
        Observable observer = RetrofitUtils.getInstance().changePassWord(s, code, pas);
        return observer;
    }

    @Override
    public Observable bindPhone(String loginType, String userName, String thirdNum,
                                String isRegister, String password, String captcha) {
        Observable observer = RetrofitUtils.getInstance().bindPhone(loginType,userName,thirdNum, isRegister,password,captcha);
        return observer;
    }

}
