package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/14 0014.
 */

public class CheckDryOrderBean {

    /**
     * page : {"currPage":1,"list":[{"cancelReasonids":null,"code":"111502776601746","createtime":"2017-08-15 14:47:55","detailList":[{"clothName":"小皮裤","clothestypeid":2,"count":2,"createtime":"2017-08-15 13:56:41","id":2,"orderid":1,"price":30,"totalprice":60},{"clothName":"大夹克","clothestypeid":1,"count":2,"createtime":"2017-08-15 13:56:41","id":1,"orderid":1,"price":50,"totalprice":100}],"expectarrivaltime":"2017-02-21 00:22:00","id":1,"logStatus":2,"mobile":"123123123","orderDetailIds":"1,2","payStatus":0,"reasonContent":null,"status":1,"totalprice":160,"uid":1}],"pageSize":10,"totalCount":1,"totalPage":1}
     * errCode : 0
     * msg : success
     */

    private PageBean page;
    private int errCode;
    private String msg;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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

    public static class PageBean {
        /**
         * currPage : 1
         * list : [{"cancelReasonids":null,"code":"111502776601746","createtime":"2017-08-15 14:47:55","detailList":[{"clothName":"小皮裤","clothestypeid":2,"count":2,"createtime":"2017-08-15 13:56:41","id":2,"orderid":1,"price":30,"totalprice":60},{"clothName":"大夹克","clothestypeid":1,"count":2,"createtime":"2017-08-15 13:56:41","id":1,"orderid":1,"price":50,"totalprice":100}],"expectarrivaltime":"2017-02-21 00:22:00","id":1,"logStatus":2,"mobile":"123123123","orderDetailIds":"1,2","payStatus":0,"reasonContent":null,"status":1,"totalprice":160,"uid":1}]
         * pageSize : 10
         * totalCount : 1
         * totalPage : 1
         */

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cancelReasonids : null
             * code : 111502776601746
             * createtime : 2017-08-15 14:47:55
             * detailList : [{"clothName":"小皮裤","clothestypeid":2,"count":2,"createtime":"2017-08-15 13:56:41","id":2,"orderid":1,"price":30,"totalprice":60},{"clothName":"大夹克","clothestypeid":1,"count":2,"createtime":"2017-08-15 13:56:41","id":1,"orderid":1,"price":50,"totalprice":100}]
             * expectarrivaltime : 2017-02-21 00:22:00
             * id : 1
             * logStatus : 2
             * mobile : 123123123
             * orderDetailIds : 1,2
             * payStatus : 0
             * reasonContent : null
             * status : 1
             * totalprice : 160
             * uid : 1
             */

            private Object cancelReasonids;
            private String code;
            private String createtime;
            private String expectarrivaltime;
            private int id;
            private int logStatus;
            private String mobile;
            private String orderDetailIds;
            private int payStatus;
            private Object reasonContent;
            private int status;
            private int totalprice;
            private int uid;
            private List<DetailListBean> detailList;

            public Object getCancelReasonids() {
                return cancelReasonids;
            }

            public void setCancelReasonids(Object cancelReasonids) {
                this.cancelReasonids = cancelReasonids;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getExpectarrivaltime() {
                return expectarrivaltime;
            }

            public void setExpectarrivaltime(String expectarrivaltime) {
                this.expectarrivaltime = expectarrivaltime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getLogStatus() {
                return logStatus;
            }

            public void setLogStatus(int logStatus) {
                this.logStatus = logStatus;
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

            public int getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(int payStatus) {
                this.payStatus = payStatus;
            }

            public Object getReasonContent() {
                return reasonContent;
            }

            public void setReasonContent(Object reasonContent) {
                this.reasonContent = reasonContent;
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

            public List<DetailListBean> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<DetailListBean> detailList) {
                this.detailList = detailList;
            }

            public static class DetailListBean {
                /**
                 * clothName : 小皮裤
                 * clothestypeid : 2
                 * count : 2
                 * createtime : 2017-08-15 13:56:41
                 * id : 2
                 * orderid : 1
                 * price : 30
                 * totalprice : 60
                 */

                private String clothName;
                private int clothestypeid;
                private int count;
                private String createtime;
                private int id;
                private int orderid;
                private int price;
                private int totalprice;

                public String getClothName() {
                    return clothName;
                }

                public void setClothName(String clothName) {
                    this.clothName = clothName;
                }

                public int getClothestypeid() {
                    return clothestypeid;
                }

                public void setClothestypeid(int clothestypeid) {
                    this.clothestypeid = clothestypeid;
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
