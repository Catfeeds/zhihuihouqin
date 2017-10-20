package com.moe.wl.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface AccountComplainModel extends MvpModel {
    Observable submitAccountComplain(String userName, String password, String idcard, String mobile, String realname, String reason);
}
