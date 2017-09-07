package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/6 0006.
 */

public class WuyeHomeBean {

    /**
     * msg : success
     * timelist : [{"barberid":1,"endtime":"11:00","name":"上午","schedulelist":[{"barberid":1,"endtime":"9:00","periodtypeid":1,"periodtypename":"上午","scheduleid":1,"starttime":"8:00","status":0}],"starttime":"8:00","typeid":1},{"barberid":1,"endtime":"17:00","name":"下午","schedulelist":[{"barberid":1,"endtime":"14:00","periodtypeid":2,"periodtypename":"下午","scheduleid":2,"starttime":"13:00","status":1},{"barberid":1,"endtime":"15:00","periodtypeid":2,"periodtypename":"下午","scheduleid":3,"starttime":"14:00","status":1}],"starttime":"13:00","typeid":2}]
     * errCode : 0
     * itemlist : [{"itemlist":[{"createtime":"2017-08-23 14:25:34","id":2,"name":"板寸","price":12,"typeid":1},{"createtime":"2017-08-23 14:24:58","id":1,"name":"平头","price":11,"typeid":1}],"maxprice":20,"minprice":10,"name":"普剪","typeid":1},{"itemlist":[{"createtime":"2017-08-23 14:25:54","id":3,"name":"拉直","price":22,"typeid":2}],"maxprice":30,"minprice":20,"name":"精剪","typeid":2}]
     */

    private String msg;
    private int errCode;
    private List<TimelistBean> timelist;
    private List<ItemlistBeanX> itemlist;

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

    public List<ItemlistBeanX> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemlistBeanX> itemlist) {
        this.itemlist = itemlist;
    }

    public static class TimelistBean {
        /**
         * barberid : 1
         * endtime : 11:00
         * name : 上午
         * schedulelist : [{"barberid":1,"endtime":"9:00","periodtypeid":1,"periodtypename":"上午","scheduleid":1,"starttime":"8:00","status":0}]
         * starttime : 8:00
         * typeid : 1
         */

        private int barberid;
        private String endtime;
        private String name;
        private String starttime;
        private int typeid;
        private List<SchedulelistBean> schedulelist;

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public List<SchedulelistBean> getSchedulelist() {
            return schedulelist;
        }

        public void setSchedulelist(List<SchedulelistBean> schedulelist) {
            this.schedulelist = schedulelist;
        }

        public static class SchedulelistBean {
            /**
             * barberid : 1
             * endtime : 9:00
             * periodtypeid : 1
             * periodtypename : 上午
             * scheduleid : 1
             * starttime : 8:00
             * status : 0
             */

            private int barberid;
            private String endtime;
            private int periodtypeid;
            private String periodtypename;
            private int scheduleid;
            private String starttime;
            private int status;

            public int getBarberid() {
                return barberid;
            }

            public void setBarberid(int barberid) {
                this.barberid = barberid;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getPeriodtypeid() {
                return periodtypeid;
            }

            public void setPeriodtypeid(int periodtypeid) {
                this.periodtypeid = periodtypeid;
            }

            public String getPeriodtypename() {
                return periodtypename;
            }

            public void setPeriodtypename(String periodtypename) {
                this.periodtypename = periodtypename;
            }

            public int getScheduleid() {
                return scheduleid;
            }

            public void setScheduleid(int scheduleid) {
                this.scheduleid = scheduleid;
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

    public static class ItemlistBeanX {
        /**
         * itemlist : [{"createtime":"2017-08-23 14:25:34","id":2,"name":"板寸","price":12,"typeid":1},{"createtime":"2017-08-23 14:24:58","id":1,"name":"平头","price":11,"typeid":1}]
         * maxprice : 20
         * minprice : 10
         * name : 普剪
         * typeid : 1
         */

        private int maxprice;
        private int minprice;
        private String name;
        private int typeid;
        private List<ItemlistBean> itemlist;

        public int getMaxprice() {
            return maxprice;
        }

        public void setMaxprice(int maxprice) {
            this.maxprice = maxprice;
        }

        public int getMinprice() {
            return minprice;
        }

        public void setMinprice(int minprice) {
            this.minprice = minprice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public List<ItemlistBean> getItemlist() {
            return itemlist;
        }

        public void setItemlist(List<ItemlistBean> itemlist) {
            this.itemlist = itemlist;
        }

        public static class ItemlistBean {
            /**
             * createtime : 2017-08-23 14:25:34
             * id : 2
             * name : 板寸
             * price : 12
             * typeid : 1
             */

            private String createtime;
            private int id;
            private String name;
            private int price;
            private int typeid;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getTypeid() {
                return typeid;
            }

            public void setTypeid(int typeid) {
                this.typeid = typeid;
            }
        }
    }
}
