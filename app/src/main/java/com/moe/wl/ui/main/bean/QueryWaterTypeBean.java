package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class QueryWaterTypeBean {

    /**
     * categoryList : [{"createtime":"2017-08-23 18:59:24","id":3,"pskucataid":null,"skucataname":"瓶装水"},{"createtime":"2017-08-23 18:58:46","id":2,"pskucataid":null,"skucataname":"灌装水"},{"createtime":"2017-08-23 18:58:31","id":1,"pskucataid":null,"skucataname":"桶装水"}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<CategoryListBean> categoryList;

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

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public static class CategoryListBean {
        /**
         * createtime : 2017-08-23 18:59:24
         * id : 3
         * pskucataid : null
         * skucataname : 瓶装水
         */

        private String createtime;
        private int id;
        private Object pskucataid;
        private String skucataname;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getPskucataid() {
            return pskucataid;
        }

        public void setPskucataid(Object pskucataid) {
            this.pskucataid = pskucataid;
        }

        public String getSkucataname() {
            return skucataname;
        }

        public void setSkucataname(String skucataname) {
            this.skucataname = skucataname;
        }
    }
}
