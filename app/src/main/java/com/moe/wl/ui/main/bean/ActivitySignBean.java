package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class ActivitySignBean {

    /**
     * msg : 您已报过该活动 ，不可重复报名
     * errCode : 1300
     */

    private String msg;
    private int errCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
