package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/8 0008.
 */

public class JieYueBean {

    /**
     * msg : 订单含有已借阅或已下架的图书，请检查后，重新下单
     * errCode : 1401
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
