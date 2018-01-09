package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/18 0018
 */

public class InformationDetailBean {
    /**
     * praiseNum : 0
     * noticeInfo : {"content":"地球将在10天内毁灭","createtime":"2017-08-23 16:32:11","favorNum":0,"id":1,"img":"123","isRecommend":0,"source":"联合国","title":"大新闻","typeid":3,"url":"1332"}
     * favorNum : 1
     * errCode : 0
     * msg : success
     * commentList : [{"content":"打的不错","createtime":"2017-08-23 16:44:23","id":2,"noticeid":1,"photo":"http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg","uid":1,"username":"13263280712"}]
     */

    private int praiseNum;
    private NoticeInfoBean noticeInfo;
    private int favorNum;
    private int errCode;
    private String msg;
    private List<CommentListEntity> commentList;

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public NoticeInfoBean getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfoBean noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public int getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(int favorNum) {
        this.favorNum = favorNum;
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

    public List<CommentListEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListEntity> commentList) {
        this.commentList = commentList;
    }

    public static class NoticeInfoBean {
        /**
         * content : 地球将在10天内毁灭
         * createtime : 2017-08-23 16:32:11
         * favorNum : 0
         * id : 1
         * img : 123
         * isRecommend : 0
         * source : 联合国
         * title : 大新闻
         * typeid : 3
         * url : 1332
         */

        private String content;
        private String createtime;
        private int favorNum;
        private int id;
        private String img;
        private int isRecommend;
        private String source;
        private String title;
        private int typeid;
        private String url;

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

        public int getFavorNum() {
            return favorNum;
        }

        public void setFavorNum(int favorNum) {
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

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class CommentListEntity {
        /**
         * content : 打的不错
         * createtime : 2017-08-23 16:44:23
         * id : 2
         * noticeid : 1
         * photo : http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg
         * uid : 1
         * username : 13263280712
         */

        private String content;
        private String createtime;
        private int id;
        private int noticeid;
        private String photo;
        private int uid;
        private String username;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(int noticeid) {
            this.noticeid = noticeid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

   /* private int praiseNum;
    private NoticeInfoEntity noticeInfo;
    private int favorNum;
    private int errCode;
    private String msg;
    private List<CommentListEntity> commentList;

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public NoticeInfoEntity getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(NoticeInfoEntity noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    public int getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(int favorNum) {
        this.favorNum = favorNum;
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

    public List<CommentListEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListEntity> commentList) {
        this.commentList = commentList;
    }

    public static class NoticeInfoEntity {

        private String content;
        private String createtime;
        private int favorNum;
        private int id;
        private String img;
        private int isRecommend;
        private String source;
        private String title;
        private int typeid;
        private String url;

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

        public int getFavorNum() {
            return favorNum;
        }

        public void setFavorNum(int favorNum) {
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

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class CommentListEntity {

        private String content;
        private String createtime;
        private int id;
        private int noticeid;
        private String photo;
        private int uid;
        private String username;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNoticeid() {
            return noticeid;
        }

        public void setNoticeid(int noticeid) {
            this.noticeid = noticeid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }*/

}
