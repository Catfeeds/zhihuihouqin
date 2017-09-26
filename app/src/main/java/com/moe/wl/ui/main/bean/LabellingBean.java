package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：投诉标签bean
 * 作者：Shixhe On 2017/9/6 0006
 */

public class LabellingBean {

    private int errCode;
    private String msg;
    private List<TagListEntity> tagList;

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

    public List<TagListEntity> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagListEntity> tagList) {
        this.tagList = tagList;
    }

    public static class TagListEntity {

        private int count;
        private String createtime;
        private int id;
        private String tagName;
        private boolean isSelect;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }
}
