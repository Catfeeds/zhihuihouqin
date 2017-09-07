package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/1 0001.
 */

public class DoctorDetailBean {

    /**
     * msg : success
     * errCode : 0
     * brief : 123
     * schedules : [{"endtime":"10:00","id":1,"periodtype":1,"starttime":"9:00","status":0},{"endtime":"11:00","id":2,"periodtype":1,"starttime":"10:00","status":1}]
     */
    private int totalcommentcount;
    private String msg;
    private int errCode;
    private String brief;
    private List<SchedulesBean> schedules;

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<SchedulesBean> getSchedules() {
        return schedules;
    }

    public int getTotalcommentcount() {
        return totalcommentcount;
    }

    public void setSchedules(List<SchedulesBean> schedules) {
        this.schedules = schedules;
    }

    public static class SchedulesBean {
        /**
         * endtime : 10:00
         * id : 1
         * periodtype : 1
         * starttime : 9:00
         * status : 0
         */

        private String endtime;
        private int id;
        private int periodtype;
        private String starttime;
        private int status;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
