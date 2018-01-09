package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/12/12 0012.
 */

public class BookOrderDetailBean {

    /**
     * msg : success
     * errCode : 0
     * detail : {"author":"2","createtime":"2017-08-25 19:40","img":"2","invitegetbooktime":"10-11","ordercode":"031503661243801","orderid":12,"orderstatus":1,"publisher":"2","title":"2"}
     */

    private String msg;
    private int errCode;
    private DetailBean detail;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * author : 2
         * createtime : 2017-08-25 19:40
         * img : 2
         * invitegetbooktime : 10-11
         * ordercode : 031503661243801
         * orderid : 12
         * orderstatus : 1
         * publisher : 2
         * title : 2
         */

        private String author;
        private String createtime;
        private String img;
        private String invitegetbooktime;
        private String ordercode;
        private int orderid;
        private int orderstatus;
        private String publisher;
        private String title;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInvitegetbooktime() {
            return invitegetbooktime;
        }

        public void setInvitegetbooktime(String invitegetbooktime) {
            this.invitegetbooktime = invitegetbooktime;
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

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
