package com.moe.wl.ui.login.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.login.bean.Auth;
import com.moe.wl.ui.login.bean.CarInfo;
import com.moe.wl.ui.login.model.AuthModel;

import java.util.List;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class AuthModelImpl implements AuthModel {

    @Override
    public Observable submitAuth(Auth auth,List<CarInfo> list) {
        Log.e("AuthModelImpl","--->获取了数据");
        Observable observer = RetrofitUtils.getInstance().submitAuth(auth,list);
        return observer ;
    }
}
