package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class SelectTimeBean {

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

        private String endtime;
        private int id;
        private int periodtype;
        private String starttime;
        private String timeStr;

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPeriodtype() {
            return periodtype;
        }

        public void setPeriodtype(int periodtype) {
            this.periodtype = periodtype;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }
    }

    public static class PmListEntity {

        private String endtime;
        private int id;
        private int periodtype;
        private String starttime;
        private String timeStr;

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPeriodtype() {
            return periodtype;
        }

        public void setPeriodtype(int periodtype) {
            this.periodtype = periodtype;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }
    }

    @Override
    public String toString() {
        return "SelectTimeBean{" +
                "amList=" + amList +
                ", errCode=" + errCode +
                ", msg='" + msg + '\'' +
                ", pmList=" + pmList +
                '}';
    }
}
