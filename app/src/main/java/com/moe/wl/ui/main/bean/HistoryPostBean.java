package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/22 0022.
 */

public class HistoryPostBean {

    /**
     * page : {"currPage":1,"list":[{"createtime":"2017-09-15 15:22:59","failedReason":"商品平台已存在","id":1,"mobile":"13264352132","productcount":10,"productname":"钢弹模型","remark":"不要最好的只要最贵的","status":3,"uid":2,"username":"小兰"}],"pageSize":10,"totalCount":1,"totalPage":1}
     * errCode : 0
     * msg : success
     */

    private PageBean page;
    private int errCode;
    private String msg;

    @Override
    public String toString() {
        return "HistoryPostBean{" +
                "page=" + page +
                ", errCode=" + errCode +
                ", msg='" + msg + '\'' +
                '}';
    }

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
         * list : [{"createtime":"2017-09-15 15:22:59","failedReason":"商品平台已存在","id":1,"mobile":"13264352132","productcount":10,"productname":"钢弹模型","remark":"不要最好的只要最贵的","status":3,"uid":2,"username":"小兰"}]
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

        public static class ListBean implements Serializable{
            /**
             * createtime : 2017-09-15 15:22:59
             * failedReason : 商品平台已存在
             * id : 1
             * mobile : 13264352132
             * productcount : 10
             * productname : 钢弹模型
             * remark : 不要最好的只要最贵的
             * status : 3
             * uid : 2
             * username : 小兰
             */

            private String createtime;
            private String failedReason;
            private int id;
            private String mobile;
            private int productcount;
            private String productname;
            private String remark;
            private int status;
            private int uid;
            private String username;

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getFailedReason() {
                return failedReason;
            }

            public void setFailedReason(String failedReason) {
                this.failedReason = failedReason;
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

            public int getProductcount() {
                return productcount;
            }

            public void setProductcount(int productcount) {
                this.productcount = productcount;
            }

            public String getProductname() {
                return productname;
            }

            public void setProductname(String productname) {
                this.productname = productname;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
