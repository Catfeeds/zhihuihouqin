package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/9 0009
 */

public class ComplainDetailBean {

    private int errCode;
    private String msg;
    private ComplaintEntity complaint;

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

    public ComplaintEntity getComplaint() {
        return complaint;
    }

    public void setComplaint(ComplaintEntity complaint) {
        this.complaint = complaint;
    }

    public static class ComplaintEntity {

        private int anonymous;
        private String complaintContent;
        private String createtime;
        private int id;
        private String imgsStr;
        private String suggestContent;
        private String tagName;
        private int tagid;
        private String uid;
        private List<String> imgs;

        public int getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
        }

        public String getComplaintContent() {
            return complaintContent;
        }

        public void setComplaintContent(String complaintContent) {
            this.complaintContent = complaintContent;
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

        public String getImgsStr() {
            return imgsStr;
        }

        public void setImgsStr(String imgsStr) {
            this.imgsStr = imgsStr;
        }

        public String getSuggestContent() {
            return suggestContent;
        }

        public void setSuggestContent(String suggestContent) {
            this.suggestContent = suggestContent;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public int getTagid() {
            return tagid;
        }

        public void setTagid(int tagid) {
            this.tagid = tagid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
