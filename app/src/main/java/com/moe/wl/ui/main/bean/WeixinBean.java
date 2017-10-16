package com.moe.wl.ui.main.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 我的电脑 on 2017/10/16 0016.
 */

public class WeixinBean {

    /**
     * msg : success
     * package : Sign=WXPay
     * paySign : B46B75AA8529D51EA9325B6954CAC72A
     * errCode : 0
     * appid : wx4ec0b35473fa09da
     * partnerid : 1467892602
     * prepayid : wx20170930165638d0633491a30777462163
     * noncestr : 9513396e8e92b8d2475c3f9437053196
     * timestamp : 1506761447
     */

    private String msg;
    @SerializedName("package")
    private String packageX;
    private String paySign;
    private int errCode;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
