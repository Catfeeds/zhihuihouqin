package com.moe.wl.ui.main.bean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/19 0019
 */

public class OrderConferenceDetailBean {

    private DetailEntity detail;
    private int errCode;
    private String msg;

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

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

    public static class DetailEntity {

        private int roomid;
        private String createtime;
        private int id;
        private int conferencetype;
        private int checkstatus;
        private int status;
        private String remark;
        private String attentdleader;
        private String conferencename;
        private int attendnum;
        private String ordercode;
        private String serviceMobile;

        public int getRoomid() {
            return roomid;
        }

        public void setRoomid(int roomid) {
            this.roomid = roomid;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getConferencetype() {
            return conferencetype;
        }

        public void setConferencetype(int conferencetype) {
            this.conferencetype = conferencetype;
        }

        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getAttentdleader() {
            return attentdleader;
        }

        public void setAttentdleader(String attentdleader) {
            this.attentdleader = attentdleader;
        }

        public String getConferencename() {
            return conferencename;
        }

        public void setConferencename(String conferencename) {
            this.conferencename = conferencename;
        }

        public int getAttendnum() {
            return attendnum;
        }

        public void setAttendnum(int attendnum) {
            this.attendnum = attendnum;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getServiceMobile() {
            return serviceMobile;
        }

        public void setServiceMobile(String serviceMobile) {
            this.serviceMobile = serviceMobile;
        }
    }
}
