package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/1 0001.
 */

public class CollectBean {

    /**
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    /**
     * status : 0
     */

    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
