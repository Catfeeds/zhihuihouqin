package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/22 0022
 */

public class ServiceMyBean {

    private int errCode;
    private String msg;
    private List<ServiceListEntity> serviceList;

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

    public List<ServiceListEntity> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceListEntity> serviceList) {
        this.serviceList = serviceList;
    }

    public static class ServiceListEntity {

        private String businesshour;
        private String detailphoto;
        private int ghprice;
        private int id;
        private String mobile;
        private String name;
        private String place;
        private String price;
        private int servicetypeid;
        private String sign;
        private String smallimg;
        private String topphoto;
        private String tradename;

        public String getBusinesshour() {
            return businesshour;
        }

        public void setBusinesshour(String businesshour) {
            this.businesshour = businesshour;
        }

        public String getDetailphoto() {
            return detailphoto;
        }

        public void setDetailphoto(String detailphoto) {
            this.detailphoto = detailphoto;
        }

        public int getGhprice() {
            return ghprice;
        }

        public void setGhprice(int ghprice) {
            this.ghprice = ghprice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(int servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSmallimg() {
            return smallimg;
        }

        public void setSmallimg(String smallimg) {
            this.smallimg = smallimg;
        }

        public String getTopphoto() {
            return topphoto;
        }

        public void setTopphoto(String topphoto) {
            this.topphoto = topphoto;
        }

        public String getTradename() {
            return tradename;
        }

        public void setTradename(String tradename) {
            this.tradename = tradename;
        }
    }
}
