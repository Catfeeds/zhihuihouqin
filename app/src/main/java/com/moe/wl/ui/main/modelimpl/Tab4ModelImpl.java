package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.Tab4Model;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab4ModelImpl implements Tab4Model {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().getUserInfo();
        return observer ;
    }

    @Override
    public Observable login(String s, String s1) {
        LogUtil.log("MainModel请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }
}
