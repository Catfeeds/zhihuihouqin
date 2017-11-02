package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/11/2 0002.
 */

public class GetChargeOrderBean {

    /**
     * errCode : 0
     * list : [{"createtime":"2017-10-20 11:11:11","weekday":"周四","status":1,"monthDay":"10-12","paytype":2,"money":11,"monthName":"本月"},{"createtime":"2017-10-20 11:11:11","weekday":"周四","status":1,"monthDay":"10-12","paytype":1,"money":10,"monthName":"本月"}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * createtime : 2017-10-20 11:11:11
         * weekday : 周四
         * status : 1
         * monthDay : 10-12
         * paytype : 2
         * money : 11
         * monthName : 本月
         */

        private String createtime;
        private String weekday;
        private int status;
        private String monthDay;
        private int paytype;
        private int money;
        private String monthName;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMonthDay() {
            return monthDay;
        }

        public void setMonthDay(String monthDay) {
            this.monthDay = monthDay;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }
    }
}
