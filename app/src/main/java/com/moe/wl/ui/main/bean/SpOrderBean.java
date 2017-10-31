package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/21 0021.
 */

public class SpOrderBean {

    /**
     * errCode : 0
     * msg : success
     * orderid : 1
     * ordertype : 1
     * ordercode : 123124243423
     * createtime : 2017-08-02 11:11:11
     */

    private int errCode;
    private String msg;
    private int orderid;
    private int ordertype;
    private String ordercode;
    private String createtime;

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

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
