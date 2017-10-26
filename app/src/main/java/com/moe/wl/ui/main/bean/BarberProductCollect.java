package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class BarberProductCollect {

    /**
     * errCode : 0
     * list : [{"barberid":19,"brief":"国家级理发师，多次获得奖项","content":null,"createtime":"2017-10-25 10:18:56","detailimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710251508897884150.jpg","id":23,"name":"哈哈","price":100,"showonindex":1,"smallimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710251508897880240.jpg","url":null},{"barberid":23,"brief":"234","content":null,"createtime":"2017-10-24 18:50:14","detailimg":"","id":22,"name":"234","price":234,"showonindex":1,"smallimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710241508842206806.jpg","url":null},{"barberid":23,"brief":"123","content":null,"createtime":"2017-10-24 18:49:59","detailimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710241508842191189.jpg","id":21,"name":"1","price":123,"showonindex":0,"smallimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710241508842188515.jpg","url":null}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * barberid : 19
         * brief : 国家级理发师，多次获得奖项
         * content : null
         * createtime : 2017-10-25 10:18:56
         * detailimg : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710251508897884150.jpg
         * id : 23
         * name : 哈哈
         * price : 100.0
         * showonindex : 1
         * smallimg : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710251508897880240.jpg
         * url : null
         */

        private int barberid;
        private String brief;
        private Object content;
        private String createtime;
        private String detailimg;
        private int id;
        private String name;
        private double price;
        private int showonindex;
        private String smallimg;
        private Object url;

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

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
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

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }
}
