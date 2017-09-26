package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public class RegistBean {

    /**
     * msg : 验证码已过期
     * errCode : 1004
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
