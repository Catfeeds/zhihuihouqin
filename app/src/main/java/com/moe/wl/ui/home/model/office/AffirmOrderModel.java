package com.moe.wl.ui.home.model.office;

import com.google.gson.JsonArray;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 办公室预订
 */

public interface AffirmOrderModel extends MvpModel {

    Observable findAvailableEquipment(String roomid, String username, String mobile, String equipmentids, String conferencetype, String conferencename,
                         String attendnum, String attentdleader, String remark, JsonArray appointmentInfo, List<String> path) ;


    Observable findAvailableEquipment(String roomid, String username, String mobile, String equipmentids, String conferencetype, String conferencename,
                           String attendnum, String attentdleader, String remark, JsonArray appointmentInfo);

}
