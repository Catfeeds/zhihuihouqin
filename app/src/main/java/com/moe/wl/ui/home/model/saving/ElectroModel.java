package com.moe.wl.ui.home.model.saving;

import mvp.cn.rx.MvpModel;
import rx.Observable;


public interface ElectroModel extends MvpModel {

    Observable getInfo(String time,int dateType,int energyType,int bid);


}
