package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/13 0013.
 */

public class OrderDryCleanBean {

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

    public static class ListEntity {

        private String createtime;
        private int id;
        private int payStatus;
        private int status;
        private String ordercode;
        private String serviceMobile;
        private List<ClothesListEntity> clothesList;

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

        public List<ClothesListEntity> getClothesList() {
            return clothesList;
        }

        public void setClothesList(List<ClothesListEntity> clothesList) {
            this.clothesList = clothesList;
        }

        public static class ClothesListEntity {

            private int price;
            private int count;
            private String clothesName;

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getClothesName() {
                return clothesName;
            }

            public void setClothesName(String clothesName) {
                this.clothesName = clothesName;
            }
        }
    }
}
