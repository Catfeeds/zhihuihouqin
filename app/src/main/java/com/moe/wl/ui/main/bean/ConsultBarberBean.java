package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class ConsultBarberBean {

    /**
     * msg : success
     * noticelist : [{"content":"123212","createtime":"2017-08-22 18:01:59","getid":1,"getreadstatus":0,"gettype":2,"id":1,"postid":1,"postreadstatus":null,"utype":1},{"content":"123212","createtime":"2017-08-22 18:03:27","getid":1,"getreadstatus":0,"gettype":2,"id":3,"postid":1,"postreadstatus":null,"utype":1}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<NoticelistBean> noticelist;

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

    public List<NoticelistBean> getNoticelist() {
        return noticelist;
    }

    public void setNoticelist(List<NoticelistBean> noticelist) {
        this.noticelist = noticelist;
    }

    public static class NoticelistBean {
        /**
         * content : 123212
         * createtime : 2017-08-22 18:01:59
         * getid : 1
         * getreadstatus : 0
         * gettype : 2
         * id : 1
         * postid : 1
         * postreadstatus : null
         * utype : 1
         */

        private String content;
        private String createtime;
        private int getid;
        private int getreadstatus;
        private int gettype;
        private int id;
        private int postid;
        private Object postreadstatus;
        private int utype;

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

        public int getGetid() {
            return getid;
        }

        public void setGetid(int getid) {
            this.getid = getid;
        }

        public int getGetreadstatus() {
            return getreadstatus;
        }

        public void setGetreadstatus(int getreadstatus) {
            this.getreadstatus = getreadstatus;
        }

        public int getGettype() {
            return gettype;
        }

        public void setGettype(int gettype) {
            this.gettype = gettype;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public Object getPostreadstatus() {
            return postreadstatus;
        }

        public void setPostreadstatus(Object postreadstatus) {
            this.postreadstatus = postreadstatus;
        }

        public int getUtype() {
            return utype;
        }

        public void setUtype(int utype) {
            this.utype = utype;
        }
    }
}
