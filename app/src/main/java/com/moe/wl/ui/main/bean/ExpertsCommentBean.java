package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/23 0023
 */

public class ExpertsCommentBean {

    private int totalcount;
    private int errCode;
    private String msg;
    private List<CommentlistEntity> commentlist;

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
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

    public List<CommentlistEntity> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistEntity> commentlist) {
        this.commentlist = commentlist;
    }

    public static class CommentlistEntity {

        private int anonymous;
        private String content;
        private String createtime;
        private int doctorid;
        private int id;
        private String imgs;
        private int orderid;
        private String photo;
        private String realname;
        private int score;
        private int servicescore;
        private int uid;

        public int getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
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

        public int getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(int doctorid) {
            this.doctorid = doctorid;
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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getServicescore() {
            return servicescore;
        }

        public void setServicescore(int servicescore) {
            this.servicescore = servicescore;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
