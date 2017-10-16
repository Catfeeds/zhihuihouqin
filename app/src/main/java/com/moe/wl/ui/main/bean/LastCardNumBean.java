package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class LastCardNumBean {

    /**
     * errCode : 0
     * lastNum : 123123131
     * msg : success
     */

    private int errCode;
    private String lastNum;
    private String msg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getLastNum() {
        return lastNum;
    }

    public void setLastNum(String lastNum) {
        this.lastNum = lastNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
