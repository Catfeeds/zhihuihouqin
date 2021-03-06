package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：订餐订单Bean
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderMealBean implements Serializable {
    /**
     * errCode : 0
     * list : [{"createtime":"2017-10-11 17:25:58","id":19,"count":5,"status":1,"payStatus":null,"sendTime":"下午 18:00-19:00","ordercode":"0151507713958478","serviceMobile":null}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * createtime : 2017-10-11 17:25:58
         * id : 19
         * count : 5
         * status : 1
         * payStatus : null
         * sendTime : 下午 18:00-19:00
         * ordercode : 0151507713958478
         * serviceMobile : null
         */

        private String createtime;
        private int id;
        private int count;
        private int status;
        private Object payStatus;
        private String sendTime;
        private String ordercode;
        private Object serviceMobile;
        int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(Object payStatus) {
            this.payStatus = payStatus;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public Object getServiceMobile() {
            return serviceMobile;
        }

        public void setServiceMobile(Object serviceMobile) {
            this.serviceMobile = serviceMobile;
        }
    }


   /* private int errCode;
    private String msg;
    private List<ListEntity> list;

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

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity {

        private String createtime;
        private int id;
        private int count;
        private int status;
        private int payStatus;
        private String sendTime;
        private String ordercode;
        private String serviceMobile;
        private int type;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }*/
}
