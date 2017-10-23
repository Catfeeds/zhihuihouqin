package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/9 0009.
 */

public class ActivityUserDetailBean {

    /**
     * msg : success
     * nation :
     * errCode : 0
     * mobile : 13344445555
     * username : null
     */

    private String msg;
    private String nation;
    private int errCode;
    private String mobile;
    private String username;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
