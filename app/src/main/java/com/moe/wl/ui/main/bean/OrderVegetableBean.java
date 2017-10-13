package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：净菜订单bean
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderVegetableBean implements Serializable {

    private int errCode;
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

    public static class ListEntity implements Serializable {

        private int id;
        private int status;
        private int payStatus;
        private String foodOriginal;
        private String ordercode;
        private String foodName;
        private String serviceMobile;
        private String foodImg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getFoodOriginal() {
            return foodOriginal;
        }

        public void setFoodOriginal(String foodOriginal) {
            this.foodOriginal = foodOriginal;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getServiceMobile() {
            return serviceMobile;
        }

        public void setServiceMobile(String serviceMobile) {
            this.serviceMobile = serviceMobile;
        }

        public String getFoodImg() {
            return foodImg;
        }

        public void setFoodImg(String foodImg) {
            this.foodImg = foodImg;
        }
    }
}
