package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/12/8 0008.
 */

public class PropertyOrderInfo {
    private String date;
    private String intervalids;

    public PropertyOrderInfo(String date, String intervalids) {
        this.date = date;
        this.intervalids = intervalids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIntervalids() {
        return intervalids;
    }

    public void setIntervalids(String intervalids) {
        this.intervalids = intervalids;
    }
}
