package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class CreateorderBean {

    /**
     * msg : success
     * orderid : 6
     * errCode : 0
     * price : 1.25
     * ordertype : 6
     */

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
    }
}
