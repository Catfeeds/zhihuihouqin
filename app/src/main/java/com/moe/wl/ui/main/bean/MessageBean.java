package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */

public class MessageBean {

    private int errCode;
    private String msg;
    private List<TalkListEntity> talkList;
    private List<MessageListEntity> messageList;

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

    public List<TalkListEntity> getTalkList() {
        return talkList;
    }

    public void setTalkList(List<TalkListEntity> talkList) {
        this.talkList = talkList;
    }

    public List<MessageListEntity> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageListEntity> messageList) {
        this.messageList = messageList;
    }

    public static class TalkListEntity {

        private String newContent;
        private String servicePhoto;
        private int serviceUid;
        private String serviceUsername;
        private int targetId;
        private int targetType;
        private int uid;

        public String getNewContent() {
            return newContent;
        }

        public void setNewContent(String newContent) {
            this.newContent = newContent;
        }

        public String getServicePhoto() {
            return servicePhoto;
        }

        public void setServicePhoto(String servicePhoto) {
            this.servicePhoto = servicePhoto;
        }

        public int getServiceUid() {
            return serviceUid;
        }

        public void setServiceUid(int serviceUid) {
            this.serviceUid = serviceUid;
        }

        public String getServiceUsername() {
            return serviceUsername;
        }

        public void setServiceUsername(String serviceUsername) {
            this.serviceUsername = serviceUsername;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public int getTargetType() {
            return targetType;
        }

        public void setTargetType(int targetType) {
            this.targetType = targetType;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }

    public static class MessageListEntity {

        private String content;
        private String createtime;
        private int id;
        private String messagetitle;
        private int messagetype;
        private int newCount;
        private int read;

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

        public String getMessagetitle() {
            return messagetitle;
        }

        public void setMessagetitle(String messagetitle) {
            this.messagetitle = messagetitle;
        }

        public int getMessagetype() {
            return messagetype;
        }

        public void setMessagetype(int messagetype) {
            this.messagetype = messagetype;
        }

        public int getNewCount() {
            return newCount;
        }

        public void setNewCount(int newCount) {
            this.newCount = newCount;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }
    }
}
