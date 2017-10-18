package com.moe.wl.ui.home.bean.office;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class SubscribeTimeResponse {


    /**
     * errCode : 0
     * msg : success
     * appointmentList : [{"durationstr":"8:00-9:00","intervalid":1,"status":2},{"durationstr":"9:00-10:00","intervalid":2,"status":2},{"durationstr":"10:00-11:00","intervalid":3,"status":2},{"durationstr":"11:00-12:00","intervalid":4,"status":2},{"durationstr":"14:00-15:00","intervalid":5,"status":2},{"durationstr":"15:00-16:00","intervalid":6,"status":1},{"durationstr":"16:00-17:00","intervalid":7,"status":1},{"durationstr":"17:00-18:00","intervalid":8,"status":3}]
     */

    private int errCode;
    private String msg;
    private List<AppointmentListBean> appointmentList;

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

    public List<AppointmentListBean> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<AppointmentListBean> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public static class AppointmentListBean {
        /**
         * durationstr : 8:00-9:00
         * intervalid : 1
         * status : 2
         */

        private String durationstr;
        private int intervalid;
        private int status;

        public String getDurationstr() {
            return durationstr;
        }

        public void setDurationstr(String durationstr) {
            this.durationstr = durationstr;
        }

        public int getIntervalid() {
            return intervalid;
        }

        public void setIntervalid(int intervalid) {
            this.intervalid = intervalid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
