package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class Order {
    /*"barberid":1,
        "mobile":132,
        "remark":"你好",
        "price":1.2,
        "scheduleid":1*/
    private String barberid;
    private String mobile;
    private String remark;
    private String price;
    private String scheduleid;

    public Order(String barberid, String mobile, String remark, String price, String scheduleid) {
        this.barberid = barberid;
        this.mobile = mobile;
        this.remark = remark;
        this.price = price;
        this.scheduleid = scheduleid;
    }
}
