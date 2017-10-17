package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/17 0017
 */

public class RepairItmeBean {

    private String msg;
    private int errCode;
    private List<ItemlistEntity> itemlist;

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

    public List<ItemlistEntity> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ItemlistEntity> itemlist) {
        this.itemlist = itemlist;
    }

    public static class ItemlistEntity {

        private int id;
        private String name;
        private int pricehighlimit;
        private int pricelowlimit;

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

        public int getPricehighlimit() {
            return pricehighlimit;
        }

        public void setPricehighlimit(int pricehighlimit) {
            this.pricehighlimit = pricehighlimit;
        }

        public int getPricelowlimit() {
            return pricelowlimit;
        }

        public void setPricelowlimit(int pricelowlimit) {
            this.pricelowlimit = pricelowlimit;
        }
    }
}
