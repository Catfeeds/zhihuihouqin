package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class ActivityHomeBean {

    /**
     * msg : success
     * errCode : 0
     * activitylist : [{"aContactMobile":"111","aContent":"1","aCreateTime":"2017-08-24 14:35:48","aId":1,"aImg":"1","aIsChecked":1,"aPlace":"1","aSignCount":1,"aSponsor":"1","aStatus":1,"aTime":"1","aTitle":"1","aTotal":1,"aUserId":1}]
     * picture :
     */

    private String msg;
    private int errCode;
    private String picture;
    private List<ActivitylistBean> activitylist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ActivitylistBean> getActivitylist() {
        return activitylist;
    }

    public void setActivitylist(List<ActivitylistBean> activitylist) {
        this.activitylist = activitylist;
    }
}
