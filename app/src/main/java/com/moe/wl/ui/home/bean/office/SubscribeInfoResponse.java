package com.moe.wl.ui.home.bean.office;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class SubscribeInfoResponse {


    /**
     * equipmentList : [{"code":"12","id":1,"name":"鲜花"},{"code":null,"id":2,"name":"投影仪"}]
     * typeList : [{"id":1,"typename":"文艺会议"},{"id":2,"typename":"小型会议"}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<EquipmentListBean> equipmentList;
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

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

}
