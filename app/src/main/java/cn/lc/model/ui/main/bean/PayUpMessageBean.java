package cn.lc.model.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：消息/缴费消息
 * 作者：Shixhe On 2017/9/25 0025
 */

public class PayUpMessageBean implements Serializable {

    private PageEntity page;
    private int errCode;
    private String msg;

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
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

    public static class PageEntity implements Serializable {

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListEntity> list;

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity implements Serializable {

            private String createtime;
            private int id;
            private String ordercode;
            private int orderid;
            private int ordertype;
            private int paymoney;
            private String payremark;
            private String paytime;
            private int paytype;
            private int status;
            private String transactionId;
            private int uid;
            private int vouchercount;
            private double voucherprice;

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

            public int getOrdertype() {
                return ordertype;
            }

            public void setOrdertype(int ordertype) {
                this.ordertype = ordertype;
            }

            public int getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(int paymoney) {
                this.paymoney = paymoney;
            }

            public String getPayremark() {
                return payremark;
            }

            public void setPayremark(String payremark) {
                this.payremark = payremark;
            }

            public String getPaytime() {
                return paytime;
            }

            public void setPaytime(String paytime) {
                this.paytime = paytime;
            }

            public int getPaytype() {
                return paytype;
            }

            public void setPaytype(int paytype) {
                this.paytype = paytype;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTransactionId() {
                return transactionId;
            }

            public void setTransactionId(String transactionId) {
                this.transactionId = transactionId;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getVouchercount() {
                return vouchercount;
            }

            public void setVouchercount(int vouchercount) {
                this.vouchercount = vouchercount;
            }

            public double getVoucherprice() {
                return voucherprice;
            }

            public void setVoucherprice(double voucherprice) {
                this.voucherprice = voucherprice;
            }
        }
    }
}
