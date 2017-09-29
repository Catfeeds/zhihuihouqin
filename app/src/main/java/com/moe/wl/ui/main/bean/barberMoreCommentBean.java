package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/28 0028.
 */

public class BarberMoreCommentBean {

    /**
     * msg : success
     * commentlist : [{"anonymous":null,"barberid":1,"content":"heool","createtime":"2017-08-24 11:56:14","id":2,"imgs":null,"orderid":8,"score":1,"uid":1}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<CommentlistBean> commentlist;

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

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }

    public static class CommentlistBean {
        /**
         * anonymous : null
         * barberid : 1
         * content : heool
         * createtime : 2017-08-24 11:56:14
         * id : 2
         * imgs : null
         * orderid : 8
         * score : 1
         * uid : 1
         */

        private Object anonymous;
        private int barberid;
        private String content;
        private String createtime;
        private int id;
        private String imgs;
        private int orderid;
        private int score;
        private int uid;
        private String photo;
        private String realname;

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(Object anonymous) {
            this.anonymous = anonymous;
        }

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
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

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
