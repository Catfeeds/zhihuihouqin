package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.MainModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class MainModelImpl implements MainModel {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }

    @Override
    public Observable login(String s, String s1) {
        LogUtil.log("MainModel请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }
}
