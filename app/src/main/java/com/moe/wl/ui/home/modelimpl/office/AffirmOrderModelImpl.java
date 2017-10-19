package com.moe.wl.ui.home.modelimpl.office;

import com.google.gson.JsonArray;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.home.model.office.AffirmOrderModel;

import java.util.List;

import rx.Observable;

/**
 * 类描述：
 * 作者：办公室预订
 */

public class AffirmOrderModelImpl implements AffirmOrderModel {

    @Override
    public Observable findAvailableEquipment(String roomid, String username, String mobile, String equipmentids, String conferencetype, String conferencename,
                                String attendnum, String attentdleader, String remark, JsonArray appointmentInfo, List<String> path) {
        Observable observable = RetrofitUtils.getInstance().findAvailableEquipment(roomid,username,mobile,equipmentids,conferencetype,conferencename,
                attendnum,attentdleader,remark,appointmentInfo,path);
        return observable;
    }

    @Override
    public Observable findAvailableEquipment(String roomid, String username, String mobile, String equipmentids, String conferencetype, String conferencename,
                                             String attendnum, String attentdleader, String remark, JsonArray appointmentInfo) {
        Observable observable = RetrofitUtils.getInstance().findAvailableEquipment(roomid,username,mobile,equipmentids,conferencetype,conferencename,
                attendnum,attentdleader,remark,appointmentInfo);
        return observable;
    }

}
