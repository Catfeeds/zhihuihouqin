package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class ReasonBean {
    /**
     * msg : success
     * servicereasonlist : [{"id":1,"reason":"不好","type":6}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<ServicereasonlistEntity> servicereasonlist;

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

    public List<ServicereasonlistEntity> getServicereasonlist() {
        return servicereasonlist;
    }

    public void setServicereasonlist(List<ServicereasonlistEntity> servicereasonlist) {
        this.servicereasonlist = servicereasonlist;
    }

    public static class ServicereasonlistEntity {
        /**
         * id : 1
         * reason : 不好
         * type : 6
         */

        private int id;
        private String reason;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

   /* private int errCode;
    private String msg;
    private List<ReasonListEntity> reasonList;

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

    public List<ReasonListEntity> getReasonList() {
        return reasonList;
    }

    public void setReasonList(List<ReasonListEntity> reasonList) {
        this.reasonList = reasonList;
    }

    public static class ReasonListEntity {

        private String content;
        private int count;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }*/
}
