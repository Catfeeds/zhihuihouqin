package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface OrderingModel extends MvpModel {

    Observable getData(String phoneNumber, int count, int type, String fixedmealtype, int duration);

    Observable getTime();

}
