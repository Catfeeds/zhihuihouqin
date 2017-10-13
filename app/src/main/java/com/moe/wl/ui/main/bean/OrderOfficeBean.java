package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderOfficeBean implements Serializable {

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
        private int count;
        private int payStatus;
        private int status;
        private int productid;
        private String dphoto;
        private String skuname;
        private String img;
        private String dusername;
        private String dmobile;
        private String ordercode;
        private String productName;
        private String serviceMobile;
        private double price;

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

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public String getDphoto() {
            return dphoto;
        }

        public void setDphoto(String dphoto) {
            this.dphoto = dphoto;
        }

        public String getSkuname() {
            return skuname;
        }

        public void setSkuname(String skuname) {
            this.skuname = skuname;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDusername() {
            return dusername;
        }

        public void setDusername(String dusername) {
            this.dusername = dusername;
        }

        public String getDmobile() {
            return dmobile;
        }

        public void setDmobile(String dmobile) {
            this.dmobile = dmobile;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getServiceMobile() {
            return serviceMobile;
        }

        public void setServiceMobile(String serviceMobile) {
            this.serviceMobile = serviceMobile;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
