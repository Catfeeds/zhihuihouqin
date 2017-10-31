package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class OfficeslistBean {

    private int errCode;
    private String msg;
    private List<OfficelistBean> officelist;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<OfficelistBean> getOfficelist() {
        return officelist;
    }

    public void setOfficelist(List<OfficelistBean> officelist) {
        this.officelist = officelist;
    }

    public static class OfficelistBean {
        /**
         * bgypright : 1
         * departid : 1
         * departname : 司局1
         * id : 1
         * name : 人事部
         * typename : null
         */

        private int bgypright;
        private int departid;
        private String departname;
        private int id;
        private String name;
        private String typename;

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

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
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

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
