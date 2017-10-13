package com.moe.wl.ui.main.bean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderVegetableDetailBean {

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

        private String createtime;
        private int totalprice;
        private int paytype;
        private String taketime;
        private int id;
        private int status;
        private int payStatus;
        private String foodOriginal;
        private String ordercode;
        private String foodName;
        private String serviceMobile;
        private String foodImg;
        private int count;


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

        public int getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(int totalprice) {
            this.totalprice = totalprice;
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

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
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

        public String getTaketime() {
            return taketime;
        }

        public void setTaketime(String taketime) {
            this.taketime = taketime;
        }

        public String getFoodImg() {
            return foodImg;
        }

        public void setFoodImg(String foodImg) {
            this.foodImg = foodImg;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
