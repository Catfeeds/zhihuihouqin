package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class CreateorderBean {
    /**
     * createtime : 2017-10-27 15:03:38
     * price : 22.0
     * errCode : 0
     * ordertype : 6
     * orderid : 123
     * ordercode : 0061509087818897
     * msg : success
     */

    private String createtime;
    private double price;
    private int errCode;
    private int ordertype;
    private int orderid;
    private String ordercode;
    private String msg;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * msg : success
     * orderid : 6
     * errCode : 0
     * price : 1.25
     * ordertype : 6
     *//*

    private String msg;
    private int orderid;
    private int errCode;
    private double price;
    private int ordertype;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }*/

}
