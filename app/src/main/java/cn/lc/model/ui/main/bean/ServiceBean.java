package cn.lc.model.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/12 0012.
 */

public class ServiceBean {

    /**
     * errCode : 0
     * serviceInfo : {"businesshour":"8:00","detailphoto":null,"id":1,"mobile":"098765","name":"预约医疗服务","place":"a301","price":null,"servicetypeid":1,"sign":"13","smallimg":null,"topphoto":"http://www.baidu.com","tradename":null}
     * msg : success
     */

    private int errCode;
    private ServiceInfoBean serviceInfo;
    private String msg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public ServiceInfoBean getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoBean serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ServiceInfoBean {
        @Override
        public String toString() {
            return "ServiceInfoBean{" +
                    "businesshour='" + businesshour + '\'' +
                    ", detailphoto='" + detailphoto + '\'' +
                    ", id=" + id +
                    ", mobile='" + mobile + '\'' +
                    ", name='" + name + '\'' +
                    ", place='" + place + '\'' +
                    ", price='" + price + '\'' +
                    ", servicetypeid=" + servicetypeid +
                    ", sign='" + sign + '\'' +
                    ", smallimg='" + smallimg + '\'' +
                    ", topphoto='" + topphoto + '\'' +
                    ", tradename='" + tradename + '\'' +
                    '}';
        }

        /**
         * businesshour : 8:00
         * detailphoto : null
         * id : 1
         * mobile : 098765
         * name : 预约医疗服务
         * place : a301
         * price : null
         * servicetypeid : 1
         * sign : 13
         * smallimg : null
         * topphoto : http://www.baidu.com
         * tradename : null
         */

        private String businesshour;
        private String detailphoto;
        private int id;
        private String mobile;
        private String name;
        private String place;
        private String price;
        private int servicetypeid;
        private String sign;
        private String smallimg;
        private String topphoto;
        private String tradename;

        public String getBusinesshour() {
            return businesshour;
        }

        public void setBusinesshour(String businesshour) {
            this.businesshour = businesshour;
        }

        public String getDetailphoto() {
            return detailphoto;
        }

        public void setDetailphoto(String detailphoto) {
            this.detailphoto = detailphoto;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getServicetypeid() {
            return servicetypeid;
        }

        public void setServicetypeid(int servicetypeid) {
            this.servicetypeid = servicetypeid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getSmallimg() {
            return smallimg;
        }

        public void setSmallimg(String smallimg) {
            this.smallimg = smallimg;
        }

        public String getTopphoto() {
            return topphoto;
        }

        public void setTopphoto(String topphoto) {
            this.topphoto = topphoto;
        }

        public String getTradename() {
            return tradename;
        }

        public void setTradename(String tradename) {
            this.tradename = tradename;
        }
    }
}
