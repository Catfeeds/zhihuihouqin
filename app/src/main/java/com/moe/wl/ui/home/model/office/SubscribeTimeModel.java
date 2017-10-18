package com.moe.wl.ui.home.model.office;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 办公室预订
 */

public interface SubscribeTimeModel extends MvpModel {

    Observable findAvailableEquipment(String roomid,String date);

}
