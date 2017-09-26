package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/9/4 0004.
 */

public class SubmitAuthBean {


    /**
     * msg : 该身份证号正在审核中，不可重复提交
     * errCode : 1016
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
