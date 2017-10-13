package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class WalletOrderBean {

    /**
     * createtime : 2017-10-10 10:08:56
     * errCode : 0
     * ordertype : 19
     * orderid : 1
     * paytype : 1
     * ordercode : 0191507601336885
     * msg : success
     */

    private String createtime;
    private int errCode;
    private int ordertype;
    private int orderid;
    private int paytype;
    private String ordercode;
    private String msg;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
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
}
