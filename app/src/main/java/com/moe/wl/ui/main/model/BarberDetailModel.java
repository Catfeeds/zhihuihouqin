package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberDetailModel extends MvpModel{

    Observable getData(int id);
    Observable collect(int type,int i);
}
