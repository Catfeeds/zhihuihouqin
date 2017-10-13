package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：专家订单bean
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderExpertBean implements Serializable {

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

        private int changes;
        private String createtime;
        private Object doctor;
        private int doctorid;
        private int ghpaystatus;
        private int id;
        private String ordercode;
        private int paystatus;
        private String photo;
        private String realname;
        private int remind;
        private String scheduledate;
        private int scheduleid;
        private int status;

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getDoctor() {
            return doctor;
        }

        public void setDoctor(Object doctor) {
            this.doctor = doctor;
        }

        public int getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(int doctorid) {
            this.doctorid = doctorid;
        }

        public int getGhpaystatus() {
            return ghpaystatus;
        }

        public void setGhpaystatus(int ghpaystatus) {
            this.ghpaystatus = ghpaystatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getRemind() {
            return remind;
        }

        public void setRemind(int remind) {
            this.remind = remind;
        }

        public String getScheduledate() {
            return scheduledate;
        }

        public void setScheduledate(String scheduledate) {
            this.scheduledate = scheduledate;
        }

        public int getScheduleid() {
            return scheduleid;
        }

        public void setScheduleid(int scheduleid) {
            this.scheduleid = scheduleid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
