package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/1 0001.
 */

public class BookDetailBean {

    private String msg;
    private String authorbrief;
    private int favorstatus;
    private int errCode;
    private String content;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAuthorbrief() {
        return authorbrief;
    }

    public void setAuthorbrief(String authorbrief) {
        this.authorbrief = authorbrief;
    }

    public int getFavorstatus() {
        return favorstatus;
    }

    public void setFavorstatus(int favorstatus) {
        this.favorstatus = favorstatus;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
