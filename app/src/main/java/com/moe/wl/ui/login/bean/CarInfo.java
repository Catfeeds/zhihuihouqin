package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class CarInfo {
    /* "cartypeid":"1",
            "precarcode":"12",
            "suffixcarcode":"23"*/
    private int cartypeid;
    private String carName = "";
    private String precarcode = "京A";
    private String suffixcarcode = "";

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCartypeid() {
        return cartypeid;
    }

    public void setCartypeid(int cartypeid) {
        this.cartypeid = cartypeid;
    }

    public String getPrecarcode() {
        return precarcode;
    }

    public void setPrecarcode(String precarcode) {
        this.precarcode = precarcode;
    }

    public String getSuffixcarcode() {
        return suffixcarcode;
    }

    public void setSuffixcarcode(String suffixcarcode) {
        this.suffixcarcode = suffixcarcode;
    }

}
