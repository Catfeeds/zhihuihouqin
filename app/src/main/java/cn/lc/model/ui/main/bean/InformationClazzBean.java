package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public class InformationClazzBean {

    private int errCode;
    private String msg;
    private List<NoticeTypeListEntity> noticeTypeList;

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

    public List<NoticeTypeListEntity> getNoticeTypeList() {
        return noticeTypeList;
    }

    public void setNoticeTypeList(List<NoticeTypeListEntity> noticeTypeList) {
        this.noticeTypeList = noticeTypeList;
    }

    public static class NoticeTypeListEntity {

        private int id;
        private String name;

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
    }
}
