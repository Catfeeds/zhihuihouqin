package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/25.
 * 描述
 */

public class PurchaseAccountListBean {

    /**
     * accountList : [{"typename":"订水账户余额","id":1,"type":2,"money":16,"ispub":1},{"typename":"订水账户余额","id":1,"type":2,"money":16,"ispub":1}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<AccountListBean> accountList;

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

    public List<AccountListBean> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountListBean> accountList) {
        this.accountList = accountList;
    }

    public static class AccountListBean {
        /**
         * typename : 订水账户余额
         * id : 1
         * type : 2
         * money : 16
         * ispub : 1
         */

        private String typename;
        private int id;
        private int type;
        private double money;
        private int ispub;

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
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

        public double getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getIspub() {
            return ispub;
        }

        public void setIspub(int ispub) {
            this.ispub = ispub;
        }
    }
}
