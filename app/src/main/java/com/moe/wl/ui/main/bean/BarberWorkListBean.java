package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class BarberWorkListBean {

    /**
     * msg : success
     * worklist : [{"barberid":1,"brief":"123","createtime":null,"detailimg":"www.baidu.com1","id":1,"name":"头型","price":1,"showonindex":1,"smallimg":"www.baidu.com"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<WorklistBean> worklist;

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

    public List<WorklistBean> getWorklist() {
        return worklist;
    }

    public void setWorklist(List<WorklistBean> worklist) {
        this.worklist = worklist;
    }

}
