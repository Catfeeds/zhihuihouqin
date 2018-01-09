package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class InforMationCollect {

    /**
     * errCode : 0
     * list : [{"content":"<p>今天下午在行政部330接待室为大家提供丰富的下午茶请大家品尝<\/p>","createtime":"2017-10-19 14:24:08","favorNum":null,"id":5,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710191508394230412.jpg","isRecommend":0,"source":"行政助理","title":"下午茶","typeName":"行政","typeid":9,"url":null}]
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
         * content : <p>今天下午在行政部330接待室为大家提供丰富的下午茶请大家品尝</p>
         * createtime : 2017-10-19 14:24:08
         * favorNum : null
         * id : 5
         * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710191508394230412.jpg
         * isRecommend : 0
         * source : 行政助理
         * title : 下午茶
         * typeName : 行政
         * typeid : 9
         * url : null
         */

        private String content;
        private String createtime;
        private Object favorNum;
        private int id;
        private String img;
        private int isRecommend;
        private String source;
        private String title;
        private String typeName;
        private int typeid;
        private Object url;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getFavorNum() {
            return favorNum;
        }

        public void setFavorNum(Object favorNum) {
            this.favorNum = favorNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }
}
