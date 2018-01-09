package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/26.
 * 描述
 */

public class BookCollect {

    /**
     * errCode : 0
     * list : [{"author":"骆驼祥子","authorbrief":"老人与海，讲述了......","bollowstatus":2,"borrowcount":10,"brief":"老人爱上了法海","content":null,"createtime":"2017-10-20 00:00:00","favorcount":82,"id":6,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:tsjy:img201710211508564158898.png","isbn":null,"isdel":0,"publisher":"北京出版社","score":4.9,"shortbrief":"值得一阅","title":"老人与海","typeid":3,"url":null},{"author":"尼古拉斯","authorbrief":"赵四","bollowstatus":2,"borrowcount":14,"brief":"似魔鬼的步伐","content":"本书讲述等等","createtime":"2017-08-25 15:08:33","favorcount":47,"id":1,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:tsjy:img201710251508915899227.png","isbn":null,"isdel":0,"publisher":"M78星云","score":4,"shortbrief":"1","title":"俄国萨瓦迪卡尼古拉斯赵四与奥特曼大战刘能之经典语录","typeid":1,"url":"1"}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<BooklistBean> list;

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

    /*public List<ListBean> getList() {
        return list;
    }*/
    public List<BooklistBean> getList() {
        return list;
    }

  /*  public void setList(List<ListBean> list) {
        this.list = list;
    }*/
    public void setList(List<BooklistBean> list) {
        this.list = list;
    }

    /*public static class ListBean {
        *//**
         * author : 骆驼祥子
         * authorbrief : 老人与海，讲述了......
         * bollowstatus : 2
         * borrowcount : 10
         * brief : 老人爱上了法海
         * content : null
         * createtime : 2017-10-20 00:00:00
         * favorcount : 82
         * id : 6
         * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:tsjy:img201710211508564158898.png
         * isbn : null
         * isdel : 0
         * publisher : 北京出版社
         * score : 4.9
         * shortbrief : 值得一阅
         * title : 老人与海
         * typeid : 3
         * url : null
         *//*

        private String author;
        private String authorbrief;
        private int bollowstatus;
        private int borrowcount;
        private String brief;
        private Object content;
        private String createtime;
        private int favorcount;
        private int id;
        private String img;
        private Object isbn;
        private int isdel;
        private String publisher;
        private double score;
        private String shortbrief;
        private String title;
        private int typeid;
        private Object url;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorbrief() {
            return authorbrief;
        }

        public void setAuthorbrief(String authorbrief) {
            this.authorbrief = authorbrief;
        }

        public int getBollowstatus() {
            return bollowstatus;
        }

        public void setBollowstatus(int bollowstatus) {
            this.bollowstatus = bollowstatus;
        }

        public int getBorrowcount() {
            return borrowcount;
        }

        public void setBorrowcount(int borrowcount) {
            this.borrowcount = borrowcount;
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

        public int getFavorcount() {
            return favorcount;
        }

        public void setFavorcount(int favorcount) {
            this.favorcount = favorcount;
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

        public Object getIsbn() {
            return isbn;
        }

        public void setIsbn(Object isbn) {
            this.isbn = isbn;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getShortbrief() {
            return shortbrief;
        }

        public void setShortbrief(String shortbrief) {
            this.shortbrief = shortbrief;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
    }*/
}
