package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/12 0012.
 */

public class FindChargeOrderBean {

    /**
     * errCode : 0
     * list : [{"monthName":"本月","monthList":[{"weekday":"周四","monthDay":"10-12","paytype":1,"money":16},{"weekday":"周四","monthDay":"10-12","paytype":1,"money":14}]},{"monthName":"8月","monthList":[{"weekday":"周三","monthDay":"08-16","paytype":1,"money":22}]},{"monthName":"2015年7月","monthList":[{"weekday":"周五","monthDay":"07-17","paytype":1,"money":1},{"weekday":"周五","monthDay":"07-17","paytype":1,"money":3}]}]
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
         * monthName : 本月
         * monthList : [{"weekday":"周四","monthDay":"10-12","paytype":1,"money":16},{"weekday":"周四","monthDay":"10-12","paytype":1,"money":14}]
         */

        private String monthName;
        private List<MonthListBean> monthList;

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }

        public List<MonthListBean> getMonthList() {
            return monthList;
        }

        public void setMonthList(List<MonthListBean> monthList) {
            this.monthList = monthList;
        }

        public static class MonthListBean {
            /**
             * weekday : 周四
             * monthDay : 10-12
             * paytype : 1
             * money : 16
             */

            private String weekday;
            private String monthDay;
            private int paytype;
            private int money;

            public String getWeekday() {
                return weekday;
            }

            public void setWeekday(String weekday) {
                this.weekday = weekday;
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
        }
    }
}
