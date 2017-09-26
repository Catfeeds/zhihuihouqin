package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class ReasonBean {

    private int errCode;
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
    }
}
