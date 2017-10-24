package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class JieYueTimeBean {

    private int errCode;
    private String msg;
    private List<AmListEntity> amList;
    private List<PmListEntity> pmList;

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

    public List<AmListEntity> getAmList() {
        return amList;
    }

    public void setAmList(List<AmListEntity> amList) {
        this.amList = amList;
    }

    public List<PmListEntity> getPmList() {
        return pmList;
    }

    public void setPmList(List<PmListEntity> pmList) {
        this.pmList = pmList;
    }

    public static class AmListEntity {

        private int id;
        private int sort;
        private String timeperiod;
        private String typeid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTimeperiod() {
            return timeperiod;
        }

        public void setTimeperiod(String timeperiod) {
            this.timeperiod = timeperiod;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }
    }

    public static class PmListEntity {

        private int id;
        private int sort;
        private String timeperiod;
        private String typeid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTimeperiod() {
            return timeperiod;
        }

        public void setTimeperiod(String timeperiod) {
            this.timeperiod = timeperiod;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }
    }
}
