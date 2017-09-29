package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/28 0028.
 */

public class BarberProductDetailBean {

    /**
     * msg : success
     * favorstatus : 0
     * errCode : 0
     * workid : 1
     * url : http://www.baidu.com
     */

    private String msg;
    private int favorstatus;
    private int errCode;
    private int workid;
    private String url;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFavorstatus() {
        return favorstatus;
    }

    public void setFavorstatus(int favorstatus) {
        this.favorstatus = favorstatus;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
