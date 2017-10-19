package com.moe.wl.ui.main.bean;

import java.io.Serializable;

/**
 * Created by 我的电脑 on 2017/10/18 0018.
 */

public class BarberlistBean implements Serializable{
    private int id;
    private String mobile;
    private String name;
    private String photo;
    private String positionName;
    private int positionid;
    private int remaincount;
    private float score;
    private int tatalcount;
    private int valid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    public int getRemaincount() {
        return remaincount;
    }

    public void setRemaincount(int remaincount) {
        this.remaincount = remaincount;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getTatalcount() {
        return tatalcount;
    }

    public void setTatalcount(int tatalcount) {
        this.tatalcount = tatalcount;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
