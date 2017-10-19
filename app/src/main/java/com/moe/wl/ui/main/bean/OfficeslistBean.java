package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class OfficeslistBean {

    /**
     * msg : success
     * errCode : 0
     * departList : [{"bgypright":1,"departid":2,"id":4,"name":"研发部"},{"bgypright":1,"departid":2,"id":3,"name":"人事部"},{"bgypright":1,"departid":1,"id":2,"name":"研发部"},{"bgypright":1,"departid":1,"id":1,"name":"人事部"}]
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
         * bgypright : 1
         * departid : 2
         * id : 4
         * name : 研发部
         */

        private int bgypright;
        private int departid;
        private int id;
        private String name;

        public int getBgypright() {
            return bgypright;
        }

        public void setBgypright(int bgypright) {
            this.bgypright = bgypright;
        }

        public int getDepartid() {
            return departid;
        }

        public void setDepartid(int departid) {
            this.departid = departid;
        }

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
