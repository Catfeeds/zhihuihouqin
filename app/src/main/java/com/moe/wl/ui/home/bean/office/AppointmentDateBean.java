package com.moe.wl.ui.home.bean.office;

import java.io.Serializable;
import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/19.
 * 描述 预约的日期
 */

public class AppointmentDateBean implements Serializable{

    private String date;
    private List<AppointmentListBean> times;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<AppointmentListBean> getTimes() {
        return times;
    }

    public void setTimes(List<AppointmentListBean> times) {
        this.times = times;
    }
}
