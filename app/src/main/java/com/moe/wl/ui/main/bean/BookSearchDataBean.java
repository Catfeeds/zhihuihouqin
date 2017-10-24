package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/24 0024
 */

public class BookSearchDataBean {

    private String msg;
    private int errCode;
    private List<TypelistEntity> typelist;
    private List<HistorylistEntity> historylist;

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

    public List<TypelistEntity> getTypelist() {
        return typelist;
    }

    public void setTypelist(List<TypelistEntity> typelist) {
        this.typelist = typelist;
    }

    public List<HistorylistEntity> getHistorylist() {
        return historylist;
    }

    public void setHistorylist(List<HistorylistEntity> historylist) {
        this.historylist = historylist;
    }

    public static class TypelistEntity {

        private String id;
        private String value;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class HistorylistEntity {

        private String createtime;
        private int id;
        private String key;
        private int uid;

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

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
