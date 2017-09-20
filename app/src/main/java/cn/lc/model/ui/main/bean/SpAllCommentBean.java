package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpAllCommentBean {

    /**
     * page : {"currPage":1,"list":[{"anonymous":0,"commenttype":1,"content":"1","createtime":"2017-08-17 17:10:26","id":1,"imgList":[],"imgs":null,"orderid":1,"photo":"http://avatar.csdn.net/C/1/8/1_u013083576.jpg","productid":1,"productname":"夹子","realname":"13263280712","score":1,"servicescore":null,"uid":1}],"pageSize":10,"totalCount":1,"totalPage":1}
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
         * list : [{"anonymous":0,"commenttype":1,"content":"1","createtime":"2017-08-17 17:10:26","id":1,"imgList":[],"imgs":null,"orderid":1,"photo":"http://avatar.csdn.net/C/1/8/1_u013083576.jpg","productid":1,"productname":"夹子","realname":"13263280712","score":1,"servicescore":null,"uid":1}]
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
             * anonymous : 0
             * commenttype : 1
             * content : 1
             * createtime : 2017-08-17 17:10:26
             * id : 1
             * imgList : []
             * imgs : null
             * orderid : 1
             * photo : http://avatar.csdn.net/C/1/8/1_u013083576.jpg
             * productid : 1
             * productname : 夹子
             * realname : 13263280712
             * score : 1
             * servicescore : null
             * uid : 1
             */

            private int anonymous;
            private int commenttype;
            private String content;
            private String createtime;
            private int id;
            private Object imgs;
            private int orderid;
            private String photo;
            private int productid;
            private String productname;
            private String realname;
            private int score;
            private Object servicescore;
            private int uid;
            private List<?> imgList;

            public int getAnonymous() {
                return anonymous;
            }

            public void setAnonymous(int anonymous) {
                this.anonymous = anonymous;
            }

            public int getCommenttype() {
                return commenttype;
            }

            public void setCommenttype(int commenttype) {
                this.commenttype = commenttype;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public Object getImgs() {
                return imgs;
            }

            public void setImgs(Object imgs) {
                this.imgs = imgs;
            }

            public int getOrderid() {
                return orderid;
            }

            public void setOrderid(int orderid) {
                this.orderid = orderid;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getProductid() {
                return productid;
            }

            public void setProductid(int productid) {
                this.productid = productid;
            }

            public String getProductname() {
                return productname;
            }

            public void setProductname(String productname) {
                this.productname = productname;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public Object getServicescore() {
                return servicescore;
            }

            public void setServicescore(Object servicescore) {
                this.servicescore = servicescore;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public List<?> getImgList() {
                return imgList;
            }

            public void setImgList(List<?> imgList) {
                this.imgList = imgList;
            }
        }
    }
}
