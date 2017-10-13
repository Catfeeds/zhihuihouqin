package com.moe.wl.ui.main.bean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/12 0012
 */

public class OrderRepairsDetailOneBean {

    private String msg;
    private int errCode;
    private DetailEntity detail;

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

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public static class DetailEntity {

        private String createtime;
        private String invitetime;
        private String itemname;
        private int menderid;
        private String mendermobile;
        private String mendername;
        private String menderphoto;
        private int menditem;
        private int orderchange;
        private String ordercode;
        private int orderid;
        private int orderstatus;
        private int paystatus;
        private int score;
        private String serviceplace;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getInvitetime() {
            return invitetime;
        }

        public void setInvitetime(String invitetime) {
            this.invitetime = invitetime;
        }

        public String getItemname() {
            return itemname;
        }

        public void setItemname(String itemname) {
            this.itemname = itemname;
        }

        public int getMenderid() {
            return menderid;
        }

        public void setMenderid(int menderid) {
            this.menderid = menderid;
        }

        public String getMendermobile() {
            return mendermobile;
        }

        public void setMendermobile(String mendermobile) {
            this.mendermobile = mendermobile;
        }

        public String getMendername() {
            return mendername;
        }

        public void setMendername(String mendername) {
            this.mendername = mendername;
        }

        public String getMenderphoto() {
            return menderphoto;
        }

        public void setMenderphoto(String menderphoto) {
            this.menderphoto = menderphoto;
        }

        public int getMenditem() {
            return menditem;
        }

        public void setMenditem(int menditem) {
            this.menditem = menditem;
        }

        public int getOrderchange() {
            return orderchange;
        }

        public void setOrderchange(int orderchange) {
            this.orderchange = orderchange;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getServiceplace() {
            return serviceplace;
        }

        public void setServiceplace(String serviceplace) {
            this.serviceplace = serviceplace;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
