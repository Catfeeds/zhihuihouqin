package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class CarInfo {
    /* "cartypeid":"1",
            "precarcode":"12",
            "suffixcarcode":"23"*/
    private String cartypeid;
    private String precarcode;
    private String suffixcarcode;

    public CarInfo(String cartypeid, String precarcode, String suffixcarcode) {
        this.cartypeid = cartypeid;
        this.precarcode = precarcode;
        this.suffixcarcode = suffixcarcode;
    }
}
