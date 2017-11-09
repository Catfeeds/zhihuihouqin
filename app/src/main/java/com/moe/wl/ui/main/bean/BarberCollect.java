package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class BarberCollect {

    private int errCode;
    private String msg;
    private List<BarberProductCollect.ListBean> list;

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

    public List<BarberProductCollect.ListBean> getList() {
        return list;
    }

    public void setList(List<BarberProductCollect.ListBean> list) {
        this.list = list;
    }
}
