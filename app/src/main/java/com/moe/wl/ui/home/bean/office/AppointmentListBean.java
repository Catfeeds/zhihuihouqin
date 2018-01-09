package com.moe.wl.ui.home.bean.office;

import java.io.Serializable;

/**
 * 作者 Wang
 * 日期 2017/10/19.
 * 描述 预约时间
 */

public class AppointmentListBean implements Serializable{
    /**
     * durationstr : 8:00-9:00
     * intervalid : 1
     * status : 2   // 	状态1可租用2不可租用3已租用
     */

    private String durationstr;
    private String intervalid;
    private String status;
    private boolean isCheck;

    @Override
    public String toString() {
        return "AppointmentListBean{" +
                "durationstr='" + durationstr + '\'' +
                ", intervalid='" + intervalid + '\'' +
                ", status='" + status + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getDurationstr() {
        return durationstr;
    }

    public void setDurationstr(String durationstr) {
        this.durationstr = durationstr;
    }

    public String getIntervalid() {
        return intervalid;
    }

    public void setIntervalid(String intervalid) {
        this.intervalid = intervalid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
