package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：订餐订单Bean
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderMealBean {

    private PageEntity page;
    private int errCode;
    private String msg;
    private String mobile;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static class PageEntity {

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

        public static class ListEntity {

            private String address;
            private int addressId;
            private String cancelReasonids;
            private int count;
            private String createtime;
            private int id;
            private String mobile;
            private String ordercode;
            private int payStatus;
            private int payType;
            private String realname;
            private String reasonContent;
            private String sendTime;
            private int sendfoodtime;
            private int status;
            private int totalprice;
            private int uid;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public String getCancelReasonids() {
                return cancelReasonids;
            }

            public void setCancelReasonids(String cancelReasonids) {
                this.cancelReasonids = cancelReasonids;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getOrdercode() {
                return ordercode;
            }

            public void setOrdercode(String ordercode) {
                this.ordercode = ordercode;
            }

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getReasonContent() {
                return reasonContent;
            }

            public void setReasonContent(String reasonContent) {
                this.reasonContent = reasonContent;
            }

            public String getSendTime() {
                return sendTime;
            }

            public void setSendTime(String sendTime) {
                this.sendTime = sendTime;
            }

            public int getSendfoodtime() {
                return sendfoodtime;
            }

            public void setSendfoodtime(int sendfoodtime) {
                this.sendfoodtime = sendfoodtime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTotalprice() {
                return totalprice;
            }

            public void setTotalprice(int totalprice) {
                this.totalprice = totalprice;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }
}
