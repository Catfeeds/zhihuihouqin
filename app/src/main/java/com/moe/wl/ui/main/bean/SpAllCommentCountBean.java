package com.moe.wl.ui.main.bean;

import java.io.Serializable;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpAllCommentCountBean implements Serializable{


    /**
     * total : 1
     * goodNum : 1
     * badNum : 0
     * middleNum : 0
     * errCode : 0
     * msg : success
     */

    private int total;
    private String goodNum;
    private String badNum;
    private String middleNum;
    private int errCode;
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public String getBadNum() {
        return badNum;
    }

    public void setBadNum(String badNum) {
        this.badNum = badNum;
    }

    public String getMiddleNum() {
        return middleNum;
    }

    public void setMiddleNum(String middleNum) {
        this.middleNum = middleNum;
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
}
