package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class Itemid {
    /*"itemid":1*/
    private int itemid;
    private double price;
    private int itemtypeid;

    public Itemid(int itemid, double price, int itemtypeid) {
        this.itemid = itemid;
        this.price = price;
        this.itemtypeid = itemtypeid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemtypeid() {
        return itemtypeid;
    }

    public void setItemtypeid(int itemtypeid) {
        this.itemtypeid = itemtypeid;
    }

    public Itemid(int itemid) {
        this.itemid = itemid;
    }
}
