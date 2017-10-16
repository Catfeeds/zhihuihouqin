package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/16 0016.
 */

public class ChargeOrderBean {

    /**
     * errCode : 0
     * msg : success
     * ordertype : 1
     * ordercode : 123124243423
     * createtime : 2017-08-02 11:11:11
     * paytype : 1
     */

    private int errCode;
    private String msg;
    private int ordertype;
    private long ordercode;
    private String createtime;
    private int paytype;

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

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public long getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(long ordercode) {
        this.ordercode = ordercode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }
}
