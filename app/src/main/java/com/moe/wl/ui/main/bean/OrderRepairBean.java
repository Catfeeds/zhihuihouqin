package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：报修订单列表Bean
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderRepairBean {

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

        private String createtime;
        private String invitetime;
        private String itemName;
        private String menderMobile;
        private String menderName;
        private String menderPhoto;
        private int menderid;
        private int menditem;
        private int orderId;
        private int orderchange;
        private String ordercode;
        private int orderstatus;
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

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getMenderMobile() {
            return menderMobile;
        }

        public void setMenderMobile(String menderMobile) {
            this.menderMobile = menderMobile;
        }

        public String getMenderName() {
            return menderName;
        }

        public void setMenderName(String menderName) {
            this.menderName = menderName;
        }

        public String getMenderPhoto() {
            return menderPhoto;
        }

        public void setMenderPhoto(String menderPhoto) {
            this.menderPhoto = menderPhoto;
        }

        public int getMenderid() {
            return menderid;
        }

        public void setMenderid(int menderid) {
            this.menderid = menderid;
        }

        public int getMenditem() {
            return menditem;
        }

        public void setMenditem(int menditem) {
            this.menditem = menditem;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
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

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
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
