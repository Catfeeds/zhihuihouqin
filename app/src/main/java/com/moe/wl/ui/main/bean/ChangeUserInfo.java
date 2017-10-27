package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/11 0011.
 */

public class ChangeUserInfo {
    private String url;
    private String nickName;
    private String position;

    public ChangeUserInfo(String url, String nickName, String position) {
        this.url = url;
        this.nickName = nickName;
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
