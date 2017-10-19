package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/10/19 0019.
 */

public class CartypeslistBean {

    /**
     * msg : success
     * cartypelist : [{"id":1,"typename":"别克"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<CartypelistBean> cartypelist;

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

    public List<CartypelistBean> getCartypelist() {
        return cartypelist;
    }

    public void setCartypelist(List<CartypelistBean> cartypelist) {
        this.cartypelist = cartypelist;
    }

    public static class CartypelistBean {
        /**
         * id : 1
         * typename : 别克
         */

        private int id;
        private String typename;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
