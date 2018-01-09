package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/12/5 0005.
 */

public class LaifangPersonInfo {
    private String name;
    private String idcard;

    public LaifangPersonInfo(String name, String idcard) {
        this.name = name;
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
