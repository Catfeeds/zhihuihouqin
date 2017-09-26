package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class BarberDetailBean {


    /**
     * msg : success
     * brief : 123
     * worklist : [{"barberid":1,"brief":"123","content":null,"createtime":null,"detailimg":"www.baidu.com1","id":1,"name":"头型","price":1,"showonindex":1,"smallimg":"www.baidu.com","url":null}]
     * commentlist : [{"anonymous":null,"barberid":1,"content":"heool","createtime":"2017-08-24 11:56:14","id":2,"imgs":null,"orderid":8,"score":1,"uid":1}]
     * favorstatus : 0
     * errCode : 0
     * commenttotalcount : 1
     * worktotalcount : 1
     */

    private String msg;
    private String brief;
    private int favorstatus;
    private int errCode;
    private int commenttotalcount;
    private int worktotalcount;
    private List<WorklistBean> worklist;
    private List<CommentlistBean> commentlist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getFavorstatus() {
        return favorstatus;
    }

    public void setFavorstatus(int favorstatus) {
        this.favorstatus = favorstatus;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getCommenttotalcount() {
        return commenttotalcount;
    }

    public void setCommenttotalcount(int commenttotalcount) {
        this.commenttotalcount = commenttotalcount;
    }

    public int getWorktotalcount() {
        return worktotalcount;
    }

    public void setWorktotalcount(int worktotalcount) {
        this.worktotalcount = worktotalcount;
    }

    public List<WorklistBean> getWorklist() {
        return worklist;
    }

    public void setWorklist(List<WorklistBean> worklist) {
        this.worklist = worklist;
    }

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }

    public static class WorklistBean {
        /**
         * barberid : 1
         * brief : 123
         * content : null
         * createtime : null
         * detailimg : www.baidu.com1
         * id : 1
         * name : 头型
         * price : 1
         * showonindex : 1
         * smallimg : www.baidu.com
         * url : null
         */

        private int barberid;
        private String brief;
        private Object content;
        private Object createtime;
        private String detailimg;
        private int id;
        private String name;
        private int price;
        private int showonindex;
        private String smallimg;
        private Object url;

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public String getDetailimg() {
            return detailimg;
        }

        public void setDetailimg(String detailimg) {
            this.detailimg = detailimg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getShowonindex() {
            return showonindex;
        }

        public void setShowonindex(int showonindex) {
            this.showonindex = showonindex;
        }

        public String getSmallimg() {
            return smallimg;
        }

        public void setSmallimg(String smallimg) {
            this.smallimg = smallimg;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }

   /* public static class CommentlistBean {
        *//**
         * anonymous : null
         * barberid : 1
         * content : heool
         * createtime : 2017-08-24 11:56:14
         * id : 2
         * imgs : null
         * orderid : 8
         * score : 1
         * uid : 1
         *//*

        private String anonymous;
        private int barberid;
        private String content;
        private String createtime;
        private int id;
        private String imgs;
        private int orderid;
        private int score;
        private int uid;

        public String getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(String anonymous) {
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
    }*/
}
