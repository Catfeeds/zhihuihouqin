package com.moe.wl.ui.login.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.login.model.RegistStep1Model;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep1ModelImpl implements RegistStep1Model {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }

    @Override
   // public Observable login(String s, String s1) {
    public Observable getCaptcha(String s, int i) {
        LogUtil.log("MainModel请求数据-->login");
        //Observable observer = RetrofitUtils.getInstance().getCaptcha("", "");
        Observable observer = RetrofitUtils.getInstance().getCaptcha(s,i);
        return observer ;
    }

    @Override
    public Observable bindPhone(int loginType, String userName, String thirdNum, String isRegister, String password, String captcha) {
        Log.e("bindPhone","绑定手机号获取数据");
        Observable observer = RetrofitUtils.getInstance().bindPhone(loginType,userName,thirdNum,isRegister,password,captcha);
        return observer;
    }

   /* @Override
    public Observable checkCode(String s, String s1) {
        Observable observer = RetrofitUtils.getInstance().checkCode(s,s1);
        return observer;
    }*/
}
