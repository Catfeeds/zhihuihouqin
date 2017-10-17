package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：报修订单列表Bean
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderRepairBean {

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

    public static class OrderlistEntity {

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
