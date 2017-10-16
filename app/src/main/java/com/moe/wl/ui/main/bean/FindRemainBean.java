package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/16 0016.
 */

public class FindRemainBean {

    /**
     * errCode : 0
     * cfEntity : {"cardNum":"1233221231","entityCardNumber":null,"id":1,"remainSum":10,"subsidySum":200,"userId":11}
     * msg : success
     */

    private int errCode;
    private CfEntityBean cfEntity;
    private String msg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public CfEntityBean getCfEntity() {
        return cfEntity;
    }

    public void setCfEntity(CfEntityBean cfEntity) {
        this.cfEntity = cfEntity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class CfEntityBean {
        /**
         * cardNum : 1233221231
         * entityCardNumber : null
         * id : 1
         * remainSum : 10
         * subsidySum : 200
         * userId : 11
         */

        private String cardNum;
        private Object entityCardNumber;
        private int id;
        private int remainSum;
        private int subsidySum;
        private int userId;

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public Object getEntityCardNumber() {
            return entityCardNumber;
        }

        public void setEntityCardNumber(Object entityCardNumber) {
            this.entityCardNumber = entityCardNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRemainSum() {
            return remainSum;
        }

        public void setRemainSum(int remainSum) {
            this.remainSum = remainSum;
        }

        public int getSubsidySum() {
            return subsidySum;
        }

        public void setSubsidySum(int subsidySum) {
            this.subsidySum = subsidySum;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
