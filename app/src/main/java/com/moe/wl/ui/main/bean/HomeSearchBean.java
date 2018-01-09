package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/12 0012.
 */

public class HomeSearchBean {

    /**
     * serviceList : [{"businesshour":null,"detailphoto":null,"ghprice":null,"id":60,"mobile":null,"name":"失物招领","place":null,"price":null,"servicetypeid":null,"sign":null,"smallimg":null,"topphoto":null,"tradename":null},{"businesshour":null,"detailphoto":null,"ghprice":null,"id":59,"mobile":null,"name":"活动报名","place":null,"price":null,"servicetypeid":null,"sign":null,"smallimg":null,"topphoto":null,"tradename":null}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ServiceListBean> serviceList;

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

    public List<ServiceListBean> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceListBean> serviceList) {
        this.serviceList = serviceList;
    }

    public static class ServiceListBean {
        /**
         * businesshour : null
         * detailphoto : null
         * ghprice : null
         * id : 60
         * mobile : null
         * name : 失物招领
         * place : null
         * price : null
         * servicetypeid : null
         * sign : null
         * smallimg : null
         * topphoto : null
         * tradename : null
         */

        private Object businesshour;
        private Object detailphoto;
        private Object ghprice;
        private int id;
        private Object mobile;
        private String name;
        private Object place;
        private Object price;
        private Object servicetypeid;
        private Object sign;
        private Object smallimg;
        private Object topphoto;
        private Object tradename;

        @Override
        public String toString() {
            return "ServiceListBean{" +
                    "businesshour=" + businesshour +
                    ", detailphoto=" + detailphoto +
                    ", ghprice=" + ghprice +
                    ", id=" + id +
                    ", mobile=" + mobile +
                    ", name='" + name + '\'' +
                    ", place=" + place +
                    ", price=" + price +
                    ", servicetypeid=" + servicetypeid +
                    ", sign=" + sign +
                    ", smallimg=" + smallimg +
                    ", topphoto=" + topphoto +
                    ", tradename=" + tradename +
                    '}';
        }

        public Object getBusinesshour() {
            return businesshour;
        }

        public void setBusinesshour(Object businesshour) {
            this.businesshour = businesshour;
        }

        public Object getDetailphoto() {
            return detailphoto;
        }

        public void setDetailphoto(Object detailphoto) {
            this.detailphoto = detailphoto;
        }

        public Object getGhprice() {
            return ghprice;
        }

        public void setGhprice(Object ghprice) {
            this.ghprice = ghprice;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getPlace() {
            return place;
        }

        public void setPlace(Object place) {
            this.place = place;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Object getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(Object servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

        public Object getSmallimg() {
            return smallimg;
        }

        public void setSmallimg(Object smallimg) {
            this.smallimg = smallimg;
        }

        public Object getTopphoto() {
            return topphoto;
        }

        public void setTopphoto(Object topphoto) {
            this.topphoto = topphoto;
        }

        public Object getTradename() {
            return tradename;
        }

        public void setTradename(Object tradename) {
            this.tradename = tradename;
        }
    }
}
