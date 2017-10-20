package com.moe.wl.ui.login.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.login.model.AccountComplainModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class AccountComplianModelImpl implements AccountComplainModel {

    @Override
    public Observable submitAccountComplain(String userName, String password, String idcard, String mobile, String realname, String reason) {
        Observable observer = RetrofitUtils.getInstance().accountComplain(userName, password, idcard, mobile, realname, reason);
        return observer;
    }
}
