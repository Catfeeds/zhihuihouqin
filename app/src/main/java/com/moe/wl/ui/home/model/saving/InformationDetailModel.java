package com.moe.wl.ui.home.model.saving;

import mvp.cn.rx.MvpModel;
import rx.Observable;


public interface InformationDetailModel extends MvpModel {

    Observable informationDetail(int id);


}
