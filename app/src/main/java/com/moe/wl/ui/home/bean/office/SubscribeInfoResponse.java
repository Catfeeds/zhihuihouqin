package com.moe.wl.ui.home.bean.office;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class SubscribeInfoResponse {

    private int errCode;
    private String msg;
    private List<EquipmentListBean> equipmentList;
    private List<EquipmentListBean> serviceList;
    private List<TypeListBean> typeList;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<EquipmentListBean> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentListBean> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<EquipmentListBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<EquipmentListBean> serviceList) {
        this.serviceList = serviceList;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }
}
