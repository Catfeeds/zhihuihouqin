package cn.lc.model.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */

public class MessageListBean implements Serializable {

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

    public static class PageEntity implements Serializable {

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

        public static class ListEntity implements Serializable {

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
}
