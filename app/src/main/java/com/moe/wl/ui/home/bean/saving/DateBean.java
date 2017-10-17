package com.moe.wl.ui.home.bean.saving;

/**
 * 日期
 */

public class DateBean {

    public DateBean(String date){
        setDate(date);
    }

    private String date;
    private boolean isCheck;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
