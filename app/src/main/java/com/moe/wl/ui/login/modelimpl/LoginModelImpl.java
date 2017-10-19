package com.moe.wl.ui.login.modelimpl;

import com.moe.wl.ui.login.model.LoginModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }

    @Override
    public Observable login(String s, String s1) {
        LogUtil.log("MainModel请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().login(s,s1);
        return observer ;
    }

    @Override
    public Observable thirdLogin(String thirdNum, String loginType) {
        LogUtil.log("MainModel请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().thirdLogin(thirdNum,loginType);
        return observer ;
    }

}
