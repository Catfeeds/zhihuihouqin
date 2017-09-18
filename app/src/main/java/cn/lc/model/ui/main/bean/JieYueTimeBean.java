package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class JieYueTimeBean {

    /**
     * msg : success
     * timelist : [{"id":1,"sort":1,"timeperiod":"8:00-:9:00","typeid":"1"},{"id":2,"sort":2,"timeperiod":"9:00-10:00","typeid":"2"},{"id":3,"sort":3,"timeperiod":"13:00-14:00","typeid":"3"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<TimelistBean> timelist;

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

    public List<TimelistBean> getTimelist() {
        return timelist;
    }

    public void setTimelist(List<TimelistBean> timelist) {
        this.timelist = timelist;
    }

    public static class TimelistBean {
        /**
         * id : 1
         * sort : 1
         * timeperiod : 8:00-:9:00
         * typeid : 1
         */

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
