package com.moe.wl.ui.main.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/11/10 0010
 */

public class OrderVisitorsListBean implements Serializable {

    /**
     * orderlist : [{"buildnum":"","cancelreason":"","carcode":"","cartype":"","createtime":"2017-11-14 11:16:46","expertarrivaltime":"","expertleavetime":"","id":50,"isdel":0,"mobile":"","ordercode":"0211510629406792","phonenum":"123456789","realname":"流","reason":"","roomnum":"2211","status":1,"uid":41,"unit":"教育Bu","username":"","vidnum":"110226198501272116","visitperiod":1,"visittime":"2017-11-14 12:20:00","vmobile":"13552029887","vname":"船","vpnum":10}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<OrderlistEntity> orderlist;

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

    public List<OrderlistEntity> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistEntity> orderlist) {
        this.orderlist = orderlist;
    }

    public static class OrderlistEntity implements Serializable {

        private String buildnum;
        private String cancelreason;
        private String carcode;
        private String cartype;
        private String createtime;
        private String expertarrivaltime;
        private String expertleavetime;
        private int id;
        private int isdel;
        private String mobile;
        private String ordercode;
        private String phonenum;
        private String realname;
        private String reason;
        private String roomnum;
        private int status;
        private int uid;
        private String unit;
        private String username;
        private String vidnum;
        private int visitperiod;
        private String visittime;
        private String vmobile;
        private String vname;
        private int vpnum;
        private int visitchecked;
        private List<ByuserlistEntity> byuserlist;

        public int getVisitchecked() {
            return visitchecked;
        }

        public void setVisitchecked(int visitchecked) {
            this.visitchecked = visitchecked;
        }

        public String getBuildnum() {
            return buildnum;
        }

        public void setBuildnum(String buildnum) {
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

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
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

        public String getVidnum() {
            return vidnum;
        }

        public void setVidnum(String vidnum) {
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

        public List<ByuserlistEntity> getByuserlist() {
            return byuserlist;
        }

        public void setByuserlist(List<ByuserlistEntity> byuserlist) {
            this.byuserlist = byuserlist;
        }

        public static class ByuserlistEntity implements Serializable {
            /**
             * id : 1
             * idcard : 1
             * name : 1
             * visituid : 50
             */

            @SerializedName("id")
            private int idX;
            private String idcard;
            private String name;
            private int visituid;

            public int getIdX() {
                return idX;
            }

            public void setIdX(int idX) {
                this.idX = idX;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getVisituid() {
                return visituid;
            }

            public void setVisituid(int visituid) {
                this.visituid = visituid;
            }
        }
    }
}
