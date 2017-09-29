package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class BarberDetailBean {

    /**
     * msg : success
     * brief : 123
     * worklist : [{"barberid":1,"brief":"123","content":null,"createtime":null,"detailimg":"www.baidu.com1","id":1,"name":"头型","price":1,"showonindex":1,"smallimg":"www.baidu.com","url":null}]
     * commentlist : [{"anonymous":null,"barberid":1,"content":"heool","createtime":"2017-09-04","id":null,"imgs":null,"orderid":8,"photo":"http://avatar.csdn.net/C/1/8/1_u013083576.jpg","realname":"123","score":1,"servicescore":2,"uid":1}]
     * favorstatus : 0
     * errCode : 0
     * commenttotalcount : 1
     * worktotalcount : 1
     */

    private String msg;
    private String brief;
    private int favorstatus;
    private int errCode;
    private int commenttotalcount;
    private int worktotalcount;
    private List<WorklistBean> worklist;
    private List<CommentlistBean> commentlist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public int getCommenttotalcount() {
        return commenttotalcount;
    }

    public void setCommenttotalcount(int commenttotalcount) {
        this.commenttotalcount = commenttotalcount;
    }

    public int getWorktotalcount() {
        return worktotalcount;
    }

    public void setWorktotalcount(int worktotalcount) {
        this.worktotalcount = worktotalcount;
    }

    public List<WorklistBean> getWorklist() {
        return worklist;
    }

    public void setWorklist(List<WorklistBean> worklist) {
        this.worklist = worklist;
    }

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }
}
