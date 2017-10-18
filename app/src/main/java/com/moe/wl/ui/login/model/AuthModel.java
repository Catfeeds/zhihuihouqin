package com.moe.wl.ui.login.model;

import com.moe.wl.ui.login.bean.Auth;
import com.moe.wl.ui.login.bean.CarInfo;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface AuthModel extends MvpModel{
    Observable submitAuth(Auth auth,List<CarInfo> list);
}
