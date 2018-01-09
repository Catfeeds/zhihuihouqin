package com.moe.wl.ui.home.model.saving;

import mvp.cn.rx.MvpModel;
import rx.Observable;


public interface ComparisonModel extends MvpModel {

    Observable information();
    Observable getBuildNum();
    Observable getinfo(int dateType ,int energyType,int[] bid,String date);
}
