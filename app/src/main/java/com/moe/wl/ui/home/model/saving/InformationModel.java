package com.moe.wl.ui.home.model.saving;

import mvp.cn.rx.MvpModel;
import rx.Observable;


public interface InformationModel extends MvpModel {

    Observable information();
    //Observable getHomeBanner();
    Observable getHomeList(int page,int limit);

}
