package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：干洗订单详情Bean
 * 作者：Shixhe On 2017/10/12 0012
 */

public class OrderDryClearDetailBean {

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
        private int id;
        private double totalprice;
        private int payStatus;
        private int status;
        private int logStatus;
        private int paytype;
        private int isEvaluated;
        private int ordertype;
        private String ordercode;
        private String serviceMobile;
        private List<ClothesListEntity> clothesList;
        private int checkstatus;
        private List<RemarklistEntity> remarklist;

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

        public double getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(double totalprice) {
            this.totalprice = totalprice;
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

        public int getLogStatus() {
            return logStatus;
        }

        public void setLogStatus(int logStatus) {
            this.logStatus = logStatus;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public int getIsEvaluated() {
            return isEvaluated;
        }

        public void setIsEvaluated(int isEvaluated) {
            this.isEvaluated = isEvaluated;
        }

        public int getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(int ordertype) {
            this.ordertype = ordertype;
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

        public List<RemarklistEntity> getRemarklist() {
            return remarklist;
        }

        public void setRemarklist(List<RemarklistEntity> remarklist) {
            this.remarklist = remarklist;
        }

        public static class ClothesListEntity {

            private double price;
            private int count;
            private String clothesName;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
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


        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }

        public static class RemarklistEntity {

            private String name;
            private String remark;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
