package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/6 0006.
 */

public class UserCommentBean {

    /**
     * msg : success
     * commentlist : [{"anonymous":1,"content":"2323","createtime":"2017-09-05","doctorid":1,"id":null,"imgs":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:ylfw:yygh:img201708211503304167295.jpg","orderid":2,"photo":"http://dentist.oss-cn-beijing.aliyuncs.com/ciming_console/upload/community/img/201707131499911709547.jpg","realname":"zhangsan","score":1.5,"servicescore":null,"uid":3}]
     * totalcount : 1
     * errCode : 0
     */

    private String msg;
    private int totalcount;
    private int errCode;
    private List<CommentlistBean> commentlist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }

   /* public static class CommentlistBean {
        *//**
         * anonymous : 1
         * content : 2323
         * createtime : 2017-09-05
         * doctorid : 1
         * id : null
         * imgs : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:ylfw:yygh:img201708211503304167295.jpg
         * orderid : 2
         * photo : http://dentist.oss-cn-beijing.aliyuncs.com/ciming_console/upload/community/img/201707131499911709547.jpg
         * realname : zhangsan
         * score : 1.5
         * servicescore : null
         * uid : 3
         *//*

        private int anonymous;
        private String content;
        private String createtime;
        private int doctorid;
        private Object id;
        private String imgs;
        private int orderid;
        private String photo;
        private String realname;
        private double score;
        private Object servicescore;
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

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
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
    }*/
}
