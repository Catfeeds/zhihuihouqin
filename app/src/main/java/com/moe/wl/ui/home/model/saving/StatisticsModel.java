package com.moe.wl.ui.home.model.saving;

import mvp.cn.rx.MvpModel;
import rx.Observable;


public interface StatisticsModel extends MvpModel {

    Observable getData(String time,int i,int j,int k);

}
