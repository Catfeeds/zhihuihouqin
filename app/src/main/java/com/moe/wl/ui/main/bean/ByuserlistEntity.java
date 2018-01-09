package com.moe.wl.ui.main.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 我的电脑 on 2017/12/15 0015.
 */

public  class ByuserlistEntity implements Serializable {
    /**
     * id : 1
     * idcard : 1
     * name : 1
     * visituid : 50
     */

    @SerializedName("id")
    private int id;
    private String idcard;
    private String name;
    private int visituid;
    private int vlid;

    public int getVlid() {
        return vlid;
    }

    public void setVlid(int vlid) {
        this.vlid = vlid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisituid() {
        return visituid;
    }

    public void setVisituid(int visituid) {
        this.visituid = visituid;
    }
}

