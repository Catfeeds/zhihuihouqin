package cn.lc.model.ui.login.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/4 0004.
 */

public class PositionListBean {

    /**
     * msg : success
     * positionlist : [{"id":1,"name":"yuanzhang"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<PositionlistBean> positionlist;

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

    public List<PositionlistBean> getPositionlist() {
        return positionlist;
    }

    public void setPositionlist(List<PositionlistBean> positionlist) {
        this.positionlist = positionlist;
    }

    public static class PositionlistBean {
        /**
         * id : 1
         * name : yuanzhang
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
