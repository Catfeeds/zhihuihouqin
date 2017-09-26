package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/29 0029.
 */

public class BarberWorkListBean {

    /**
     * msg : success
     * worklist : [{"barberid":1,"brief":"123","createtime":null,"detailimg":"www.baidu.com1","id":1,"name":"头型","price":1,"showonindex":1,"smallimg":"www.baidu.com"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<WorklistBean> worklist;

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

    public List<WorklistBean> getWorklist() {
        return worklist;
    }

    public void setWorklist(List<WorklistBean> worklist) {
        this.worklist = worklist;
    }

    public static class WorklistBean {
        /**
         * barberid : 1
         * brief : 123
         * createtime : null
         * detailimg : www.baidu.com1
         * id : 1
         * name : 头型
         * price : 1
         * showonindex : 1
         * smallimg : www.baidu.com
         */

        private int barberid;
        private String brief;
        private Object createtime;
        private String detailimg;
        private int id;
        private String name;
        private int price;
        private int showonindex;
        private String smallimg;

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public String getDetailimg() {
            return detailimg;
        }

        public void setDetailimg(String detailimg) {
            this.detailimg = detailimg;
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getShowonindex() {
            return showonindex;
        }

        public void setShowonindex(int showonindex) {
            this.showonindex = showonindex;
        }

        public String getSmallimg() {
            return smallimg;
        }

        public void setSmallimg(String smallimg) {
            this.smallimg = smallimg;
        }
    }
}
