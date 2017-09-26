package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public class HomePageBean {

    private int errCode;
    private String msg;
    private List<InformationBean.PageEntity.ListEntity> noticeList;
    private List<ServiceListEntity> serviceList;
    private List<BxwxOrderList> bxwxOrderList;
    private List<ActivityHomeBean.ActivitylistBean> activityList;
    private List<CarouselListEntity> carouselList;

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

    public List<InformationBean.PageEntity.ListEntity> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<InformationBean.PageEntity.ListEntity> noticeList) {
        this.noticeList = noticeList;
    }

    public List<ServiceListEntity> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceListEntity> serviceList) {
        this.serviceList = serviceList;
    }

    public List<BxwxOrderList> getBxwxOrderList() {
        return bxwxOrderList;
    }

    public void setBxwxOrderList(List<BxwxOrderList> bxwxOrderList) {
        this.bxwxOrderList = bxwxOrderList;
    }

    public List<ActivityHomeBean.ActivitylistBean> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityHomeBean.ActivitylistBean> activityList) {
        this.activityList = activityList;
    }

    public List<CarouselListEntity> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<CarouselListEntity> carouselList) {
        this.carouselList = carouselList;
    }

    public static class BxwxOrderList {
        private String createtime;
        private String invitetime;
        private String itemName;
        private String menderMobile;
        private String menderName;
        private String menderPhoto;
        private int menderid;
        private int menditem;
        private int orderId;
        private int orderchange;
        private String ordercode;
        private int orderstatus;
        private String serviceplace;
        private int uid;
        private int paystatus;
        private int score;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getInvitetime() {
            return invitetime;
        }

        public void setInvitetime(String invitetime) {
            this.invitetime = invitetime;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getMenderid() {
            return menderid;
        }

        public void setMenderid(int menderid) {
            this.menderid = menderid;
        }

        public String getMenderMobile() {
            return menderMobile;
        }

        public void setMenderMobile(String menderMobile) {
            this.menderMobile = menderMobile;
        }

        public String getMenderName() {
            return menderName;
        }

        public void setMenderName(String menderName) {
            this.menderName = menderName;
        }

        public String getMenderPhoto() {
            return menderPhoto;
        }

        public void setMenderPhoto(String menderPhoto) {
            this.menderPhoto = menderPhoto;
        }

        public int getMenditem() {
            return menditem;
        }

        public void setMenditem(int menditem) {
            this.menditem = menditem;
        }

        public int getOrderchange() {
            return orderchange;
        }

        public void setOrderchange(int orderchange) {
            this.orderchange = orderchange;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getOrderstatus() {
            return orderstatus;
        }

        public void setOrderstatus(int orderstatus) {
            this.orderstatus = orderstatus;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getServiceplace() {
            return serviceplace;
        }

        public void setServiceplace(String serviceplace) {
            this.serviceplace = serviceplace;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }


    public static class ServiceListEntity {

        private String businesshour;
        private String detailphoto;
        private int id;
        private String mobile;
        private String name;
        private String place;
        private String price;
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

    public static class CarouselListEntity {

        private int id;
        private String imgs;
        private int sort;
        private String time;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
