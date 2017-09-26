package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.Tab3Model;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab3ModelImpl implements Tab3Model {
    @Override
    public Observable getData() {
        Observable observer = RetrofitUtils.getInstance().getMessage();
        return observer ;
    }

    @Override
    public Observable login(String s, String s1) {
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }
}
