package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：净菜订单bean
 * 作者：Shixhe On 2017/9/27 0027
 */

public class OrderVegetableBean {

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

            private Object cancelReasonids;
            private String createtime;
            private String expireTime;
            private int getfoodtime;
            private int id;
            private String mobile;
            private String orderDetailIds;
            private String ordercode;
            private int payStatus;
            private int payType;
            private String realname;
            private String reasonContent;
            private int status;
            private String takeTime;
            private int totalprice;
            private int uid;
            private List<DetailListEntity> detailList;

            public Object getCancelReasonids() {
                return cancelReasonids;
            }

            public void setCancelReasonids(Object cancelReasonids) {
                this.cancelReasonids = cancelReasonids;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getExpireTime() {
                return expireTime;
            }

            public void setExpireTime(String expireTime) {
                this.expireTime = expireTime;
            }

            public int getGetfoodtime() {
                return getfoodtime;
            }

            public void setGetfoodtime(int getfoodtime) {
                this.getfoodtime = getfoodtime;
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

            public String getOrderDetailIds() {
                return orderDetailIds;
            }

            public void setOrderDetailIds(String orderDetailIds) {
                this.orderDetailIds = orderDetailIds;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTakeTime() {
                return takeTime;
            }

            public void setTakeTime(String takeTime) {
                this.takeTime = takeTime;
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

            public List<DetailListEntity> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<DetailListEntity> detailList) {
                this.detailList = detailList;
            }

            public static class DetailListEntity {

                private int count;
                private String createtime;
                private String foodImg;
                private String foodName;
                private String foodOriginal;
                private int foodid;
                private int id;
                private int orderid;
                private int price;
                private int totalprice;

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

                public String getFoodImg() {
                    return foodImg;
                }

                public void setFoodImg(String foodImg) {
                    this.foodImg = foodImg;
                }

                public String getFoodName() {
                    return foodName;
                }

                public void setFoodName(String foodName) {
                    this.foodName = foodName;
                }

                public String getFoodOriginal() {
                    return foodOriginal;
                }

                public void setFoodOriginal(String foodOriginal) {
                    this.foodOriginal = foodOriginal;
                }

                public int getFoodid() {
                    return foodid;
                }

                public void setFoodid(int foodid) {
                    this.foodid = foodid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getOrderid() {
                    return orderid;
                }

                public void setOrderid(int orderid) {
                    this.orderid = orderid;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getTotalprice() {
                    return totalprice;
                }

                public void setTotalprice(int totalprice) {
                    this.totalprice = totalprice;
                }
            }
        }
    }
}
