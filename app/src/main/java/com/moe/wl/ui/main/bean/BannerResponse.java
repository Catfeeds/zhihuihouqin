
package com.moe.wl.ui.main.bean;

import com.moe.wl.framework.base.BaseResponse;

/**
 * 轮播图解析类
 */

public class BannerResponse extends BaseResponse{

    /**
     * serviceInfo : {"businesshour":"8:00","detailphoto":null,"id":1,"mobile":"098765","name":"预约医疗服务","place":"a301","price":null,"servicetypeid":1,"sign":"13","smallimg":null,"topphoto":"http://www.baidu.com","tradename":null}
     */

    private ServiceInfoBean serviceInfo;

    public ServiceInfoBean getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(ServiceInfoBean serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public static class ServiceInfoBean {
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
