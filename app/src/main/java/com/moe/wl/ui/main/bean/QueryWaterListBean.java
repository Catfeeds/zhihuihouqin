package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class QueryWaterListBean {

    /**
     * page : {"currPage":1,"list":[{"cid":1,"createtime":"2017-08-23 19:04:20","id":3,"img":null,"name":"哇哈哈","price":25},{"cid":1,"createtime":"2017-08-23 19:04:05","id":2,"img":null,"name":"农夫山泉纯净水","price":30},{"cid":1,"createtime":"2017-08-23 19:03:45","id":1,"img":"a","name":"农夫山泉矿泉水","price":25}],"pageSize":10,"totalCount":3,"totalPage":1}
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
         * list : [{"cid":1,"createtime":"2017-08-23 19:04:20","id":3,"img":null,"name":"哇哈哈","price":25},{"cid":1,"createtime":"2017-08-23 19:04:05","id":2,"img":null,"name":"农夫山泉纯净水","price":30},{"cid":1,"createtime":"2017-08-23 19:03:45","id":1,"img":"a","name":"农夫山泉矿泉水","price":25}]
         * pageSize : 10
         * totalCount : 3
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
             * cid : 1
             * createtime : 2017-08-23 19:04:20
             * id : 3
             * img : null
             * name : 哇哈哈
             * price : 25
             */

            private int cid;
            private String createtime;
            private int id;
            private String img;
            private String name;
            private double price;
            private int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }
    }
}
