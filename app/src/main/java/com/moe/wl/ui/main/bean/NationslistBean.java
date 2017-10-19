package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class NationslistBean {

    /**
     * msg : success
     * errCode : 0
     * nationlist : [{"id":2,"name":"满族"},{"id":1,"name":"汉族"}]
     */

    private String msg;
    private int errCode;
    private List<NationlistBean> nationlist;

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

    public List<NationlistBean> getNationlist() {
        return nationlist;
    }

    public void setNationlist(List<NationlistBean> nationlist) {
        this.nationlist = nationlist;
    }

    public static class NationlistBean {
        /**
         * id : 2
         * name : 满族
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
