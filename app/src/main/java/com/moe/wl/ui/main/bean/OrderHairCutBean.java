package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：理发订单bean
 * 作者：Shixhe On 2017/9/28 0028
 */

public class OrderHairCutBean implements Serializable {

    private ServiceEntity service;
    private int errCode;
    private String msg;
    private List<OrderlistEntity> orderlist;

    public ServiceEntity getService() {
        return service;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

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

    public List<OrderlistEntity> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<OrderlistEntity> orderlist) {
        this.orderlist = orderlist;
    }

    public static class ServiceEntity implements Serializable {

        private String businesshour;
        private Object dcfwgzcfixedprice;
        private Object dcfwgzcprice;
        private String detailphoto;
        private double ghprice;
        private int id;
        private String mobile;
        private String name;
        private String place;
        private double price;
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

        public Object getDcfwgzcfixedprice() {
            return dcfwgzcfixedprice;
        }

        public void setDcfwgzcfixedprice(Object dcfwgzcfixedprice) {
            this.dcfwgzcfixedprice = dcfwgzcfixedprice;
        }

        public Object getDcfwgzcprice() {
            return dcfwgzcprice;
        }

        public void setDcfwgzcprice(Object dcfwgzcprice) {
            this.dcfwgzcprice = dcfwgzcprice;
        }

        public String getDetailphoto() {
            return detailphoto;
        }

        public void setDetailphoto(String detailphoto) {
            this.detailphoto = detailphoto;
        }

        public double getGhprice() {
            return ghprice;
        }

        public void setGhprice(double ghprice) {
            this.ghprice = ghprice;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
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

    public static class OrderlistEntity implements Serializable {

        private String addr;
        private int barberid;
        private int changes;
        private String createtime;
        private String invitetime;
        private int isdel;
        private String mobile;
        private String ordercode;
        private int orderid;
        private int paystatus;
        private String paytime;
        private int paytype;
        private String photo;
        private double price;
        private String realname;
        private int score;
        private int status;
        private int uid;
        private int checkstatus;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
        }

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

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

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public int getPaytype() {
            return paytype;
        }

        public void setPaytype(int paytype) {
            this.paytype = paytype;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }
    }
}
