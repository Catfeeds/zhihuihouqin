package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class ComplainHistoryBean {

    private PageEntity page;
    private int errCode;
    private String msg;

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
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

    public static class PageEntity {

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListEntity> list;

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {

            private int anonymous;
            private String complaintContent;
            private String createtime;
            private int id;
            private String imgsStr;
            private String suggestContent;
            private String tagName;
            private int tagid;
            private Object uid;
            private List<?> imgs;

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

            public Object getUid() {
                return uid;
            }

            public void setUid(Object uid) {
                this.uid = uid;
            }

            public List<?> getImgs() {
                return imgs;
            }

            public void setImgs(List<?> imgs) {
                this.imgs = imgs;
            }
        }
    }
}
