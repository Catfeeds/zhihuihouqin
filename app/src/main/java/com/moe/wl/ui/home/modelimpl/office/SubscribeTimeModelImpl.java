package com.moe.wl.ui.home.modelimpl.office;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.office.SubscribeTimeModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class SubscribeTimeModelImpl implements SubscribeTimeModel {

    @Override
    public Observable findAvailableEquipment(String roomid,String date) {
        Observable observable = RetrofitUtils.getInstance().findAvailableTime(roomid,date);
        return observable;
    }
}
