package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/11/10 0010
 */

public class OrderVisitorsListBean {

    private String msg;
    private int errCode;
    private List<OrderlistEntity> orderlist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<OrderlistEntity> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistEntity> orderlist) {
        this.orderlist = orderlist;
    }

    public static class OrderlistEntity {

        private int buildnum;
        private String cancelreason;
        private String carcode;
        private String cartype;
        private String createtime;
        private String expertarrivaltime;
        private String expertleavetime;
        private int id;
        private int isdel;
        private String mobile;
        private String phonenum;
        private String reason;
        private String roomnum;
        private int status;
        private int uid;
        private String unit;
        private String username;
        private int vidnum;
        private int visitperiod;
        private String visittime;
        private String vmobile;
        private String vname;
        private int vpnum;

        public int getBuildnum() {
            return buildnum;
        }

        public void setBuildnum(int buildnum) {
            this.buildnum = buildnum;
        }

        public String getCancelreason() {
            return cancelreason;
        }

        public void setCancelreason(String cancelreason) {
            this.cancelreason = cancelreason;
        }

        public String getCarcode() {
            return carcode;
        }

        public void setCarcode(String carcode) {
            this.carcode = carcode;
        }

        public String getCartype() {
            return cartype;
        }

        public void setCartype(String cartype) {
            this.cartype = cartype;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getExpertarrivaltime() {
            return expertarrivaltime;
        }

        public void setExpertarrivaltime(String expertarrivaltime) {
            this.expertarrivaltime = expertarrivaltime;
        }

        public String getExpertleavetime() {
            return expertleavetime;
        }

        public void setExpertleavetime(String expertleavetime) {
            this.expertleavetime = expertleavetime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRoomnum() {
            return roomnum;
        }

        public void setRoomnum(String roomnum) {
            this.roomnum = roomnum;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getVidnum() {
            return vidnum;
        }

        public void setVidnum(int vidnum) {
            this.vidnum = vidnum;
        }

        public int getVisitperiod() {
            return visitperiod;
        }

        public void setVisitperiod(int visitperiod) {
            this.visitperiod = visitperiod;
        }

        public String getVisittime() {
            return visittime;
        }

        public void setVisittime(String visittime) {
            this.visittime = visittime;
        }

        public String getVmobile() {
            return vmobile;
        }

        public void setVmobile(String vmobile) {
            this.vmobile = vmobile;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public int getVpnum() {
            return vpnum;
        }

        public void setVpnum(int vpnum) {
            this.vpnum = vpnum;
        }
    }
}
