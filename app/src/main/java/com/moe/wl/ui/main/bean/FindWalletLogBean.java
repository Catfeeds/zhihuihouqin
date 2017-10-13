package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class FindWalletLogBean {

    /**
     * page : {"currPage":1,"list":[{"createtime":"2017-09-30 16:51:45","detail":null,"id":5,"money":1.25,"orderid":8,"ordertype":6,"ordertypename":"预约理发","uid":3,"usetype":2},{"createtime":"2017-09-30 15:43:19","detail":null,"id":4,"money":90,"orderid":2,"ordertype":18,"ordertypename":"订水服务","uid":3,"usetype":2}],"pageSize":10,"totalCount":2,"totalPage":1}
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
         * list : [{"createtime":"2017-09-30 16:51:45","detail":null,"id":5,"money":1.25,"orderid":8,"ordertype":6,"ordertypename":"预约理发","uid":3,"usetype":2},{"createtime":"2017-09-30 15:43:19","detail":null,"id":4,"money":90,"orderid":2,"ordertype":18,"ordertypename":"订水服务","uid":3,"usetype":2}]
         * pageSize : 10
         * totalCount : 2
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
             * createtime : 2017-09-30 16:51:45
             * detail : null
             * id : 5
             * money : 1.25
             * orderid : 8
             * ordertype : 6
             * ordertypename : 预约理发
             * uid : 3
             * usetype : 2
             */

            private String createtime;
            private String detail;
            private int id;
            private double money;
            private int orderid;
            private int ordertype;
            private String ordertypename;
            private int uid;
            private int usetype;

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
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

            public String getOrdertypename() {
                return ordertypename;
            }

            public void setOrdertypename(String ordertypename) {
                this.ordertypename = ordertypename;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getUsetype() {
                return usetype;
            }

            public void setUsetype(int usetype) {
                this.usetype = usetype;
            }
        }
    }
}
