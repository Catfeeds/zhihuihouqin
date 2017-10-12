package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/11 0011.
 */

public class ChangeUserInfo {
    private String url;
    private String nickName;

    public ChangeUserInfo(String url, String nickName) {
        this.url = url;
        this.nickName = nickName;
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
}
