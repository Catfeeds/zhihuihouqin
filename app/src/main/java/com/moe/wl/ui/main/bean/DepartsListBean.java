package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class DepartsListBean {

    /**
     * msg : success
     * errCode : 0
     * departList : [{"id":2,"name":"财务部"},{"id":1,"name":"维修部"}]
     */

    private String msg;
    private int errCode;
    private List<DepartListBean> departList;

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

    public List<DepartListBean> getDepartList() {
        return departList;
    }

    public void setDepartList(List<DepartListBean> departList) {
        this.departList = departList;
    }

    public static class DepartListBean {
        /**
         * id : 2
         * name : 财务部
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
