package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionBean {

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

            private String mCalories;
            private String mContent;
            private String mCreateTime;
            private int mFavorNum;
            private int mId;
            private String mImg;
            private int mPraiseNum;
            private int mScanNum;
            private String mSource;
            private String mTitle;
            private int mType;
            private int mTimeType;

            public String getMCalories() {
                return mCalories;
            }

            public void setMCalories(String mCalories) {
                this.mCalories = mCalories;
            }

            public String getMContent() {
                return mContent;
            }

            public void setMContent(String mContent) {
                this.mContent = mContent;
            }

            public String getMCreateTime() {
                return mCreateTime;
            }

            public void setMCreateTime(String mCreateTime) {
                this.mCreateTime = mCreateTime;
            }

            public int getMFavorNum() {
                return mFavorNum;
            }

            public void setMFavorNum(int mFavorNum) {
                this.mFavorNum = mFavorNum;
            }

            public int getMId() {
                return mId;
            }

            public void setMId(int mId) {
                this.mId = mId;
            }

            public String getMImg() {
                return mImg;
            }

            public void setMImg(String mImg) {
                this.mImg = mImg;
            }

            public int getMPraiseNum() {
                return mPraiseNum;
            }

            public void setMPraiseNum(int mPraiseNum) {
                this.mPraiseNum = mPraiseNum;
            }

            public int getMScanNum() {
                return mScanNum;
            }

            public void setMScanNum(int mScanNum) {
                this.mScanNum = mScanNum;
            }

            public String getMSource() {
                return mSource;
            }

            public void setMSource(String mSource) {
                this.mSource = mSource;
            }

            public String getMTitle() {
                return mTitle;
            }

            public void setMTitle(String mTitle) {
                this.mTitle = mTitle;
            }

            public int getMType() {
                return mType;
            }

            public void setMType(int mType) {
                this.mType = mType;
            }

            public int getMTimeType() {
                return mTimeType;
            }

            public void setMTimeType(int mTimeType) {
                this.mTimeType = mTimeType;
            }
        }
    }
}
