package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/8 0008.
 */

public class ActivitylistBean implements Serializable {
    /**
     * aContactMobile : 111
     * aContent : 1
     * aCreateTime : 2017-08-24 14:35:48
     * aId : 1
     * aImg : 1
     * aIsChecked : 1
     * aPlace : 1
     * aSignCount : 1
     * aSponsor : 1
     * aStatus : 1
     * aTime : 1
     * aTitle : 1
     * aTotal : 1
     * aUserId : 1
     */

    private String aContactMobile;
    private String aContent;
    private String aCreateTime;
    private int aId;
    private String aImg;
    private int aIsChecked;
    private String aPlace;
    private int aSignCount;
    private String aSponsor;
    private int aStatus;
    private String aTime;
    private String aTitle;
    private int aTotal;
    private int aUserId;
    private List<String> aPhoto;

    public List<String> getaPhoto() {
        return aPhoto;
    }

    public void setaPhoto(List<String> aPhoto) {
        this.aPhoto = aPhoto;
    }

    public String getAContactMobile() {
        return aContactMobile;
    }

    public void setAContactMobile(String aContactMobile) {
        this.aContactMobile = aContactMobile;
    }

    public String getAContent() {
        return aContent;
    }

    public void setAContent(String aContent) {
        this.aContent = aContent;
    }

    public String getACreateTime() {
        return aCreateTime;
    }

    public void setACreateTime(String aCreateTime) {
        this.aCreateTime = aCreateTime;
    }

    public int getAId() {
        return aId;
    }

    public void setAId(int aId) {
        this.aId = aId;
    }

    public String getAImg() {
        return aImg;
    }

    public void setAImg(String aImg) {
        this.aImg = aImg;
    }

    public int getAIsChecked() {
        return aIsChecked;
    }

    public void setAIsChecked(int aIsChecked) {
        this.aIsChecked = aIsChecked;
    }

    public String getAPlace() {
        return aPlace;
    }

    public void setAPlace(String aPlace) {
        this.aPlace = aPlace;
    }

    public int getASignCount() {
        return aSignCount;
    }

    public void setASignCount(int aSignCount) {
        this.aSignCount = aSignCount;
    }

    public String getASponsor() {
        return aSponsor;
    }

    public void setASponsor(String aSponsor) {
        this.aSponsor = aSponsor;
    }

    public int getAStatus() {
        return aStatus;
    }

    public void setAStatus(int aStatus) {
        this.aStatus = aStatus;
    }

    public String getATime() {
        return aTime;
    }

    public void setATime(String aTime) {
        this.aTime = aTime;
    }

    public String getATitle() {
        return aTitle;
    }

    public void setATitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public int getATotal() {
        return aTotal;
    }

    public void setATotal(int aTotal) {
        this.aTotal = aTotal;
    }

    public int getAUserId() {
        return aUserId;
    }

    public void setAUserId(int aUserId) {
        this.aUserId = aUserId;
    }
}
