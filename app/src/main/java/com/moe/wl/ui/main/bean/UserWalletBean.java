package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/11 0011.
 */

public class UserWalletBean {

    /**
     * voucherNum : 0
     * hasBuyAuth : 0
     * payPasswordState : 0
     * publicRemain : null
     * walletRemain : 0
     * errCode : 0
     * msg : success
     */

    private int voucherNum;
    private int hasBuyAuth;
    private int payPasswordState;
    private int  publicRemain;
    private int walletRemain;
    private int errCode;
    private String msg;

    public int getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(int voucherNum) {
        this.voucherNum = voucherNum;
    }

    public int getHasBuyAuth() {
        return hasBuyAuth;
    }

    public void setHasBuyAuth(int hasBuyAuth) {
        this.hasBuyAuth = hasBuyAuth;
    }

    public int getPayPasswordState() {
        return payPasswordState;
    }

    public void setPayPasswordState(int payPasswordState) {
        this.payPasswordState = payPasswordState;
    }

    public int getPublicRemain() {
        return publicRemain;
    }

    public void setPublicRemain(int publicRemain) {
        this.publicRemain = publicRemain;
    }

    public int getWalletRemain() {
        return walletRemain;
    }

    public void setWalletRemain(int walletRemain) {
        this.walletRemain = walletRemain;
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
