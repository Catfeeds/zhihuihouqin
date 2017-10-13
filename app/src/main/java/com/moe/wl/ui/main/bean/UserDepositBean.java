package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/13 0013.
 */

public class UserDepositBean {

    /**
     * deposit : {"deposit":50,"id":1,"type":20,"uid":16}
     * errCode : 0
     * msg : success
     */

    private DepositBean deposit;
    private int errCode;
    private String msg;

    public DepositBean getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositBean deposit) {
        this.deposit = deposit;
    }

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

    public static class DepositBean {
        /**
         * deposit : 50
         * id : 1
         * type : 20
         * uid : 16
         */

        private int deposit;
        private int id;
        private int type;
        private int uid;

        public int getDeposit() {
            return deposit;
        }

        public void setDeposit(int deposit) {
            this.deposit = deposit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
