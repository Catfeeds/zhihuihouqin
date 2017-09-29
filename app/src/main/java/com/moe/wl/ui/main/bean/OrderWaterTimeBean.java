package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/27 0027.
 */

public class OrderWaterTimeBean {

    /**
     * errCode : 0
     * amList : [{"endtime":"9:00","id":2,"periodtype":1,"starttime":"8:00","timeStr":"8:00-9:00"},{"endtime":"12:00","id":1,"periodtype":1,"starttime":"11:00","timeStr":"11:00-12:00"}]
     * pmList : [{"endtime":"14:00","id":3,"periodtype":2,"starttime":"13:00","timeStr":"13:00-14:00"},{"endtime":"19:00","id":4,"periodtype":2,"starttime":"18:00","timeStr":"18:00-19:00"}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<AmListBean> amList;
    private List<PmListBean> pmList;

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

    public List<AmListBean> getAmList() {
        return amList;
    }

    public void setAmList(List<AmListBean> amList) {
        this.amList = amList;
    }

    public List<PmListBean> getPmList() {
        return pmList;
    }

    public void setPmList(List<PmListBean> pmList) {
        this.pmList = pmList;
    }

    public static class AmListBean {
        /**
         * endtime : 9:00
         * id : 2
         * periodtype : 1
         * starttime : 8:00
         * timeStr : 8:00-9:00
         */

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

    public static class PmListBean {
        /**
         * endtime : 14:00
         * id : 3
         * periodtype : 2
         * starttime : 13:00
         * timeStr : 13:00-14:00
         */

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
}
