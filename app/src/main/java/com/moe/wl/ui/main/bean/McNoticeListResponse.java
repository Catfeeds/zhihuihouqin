package com.moe.wl.ui.main.bean;

import com.moe.wl.framework.base.BaseResponse;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class McNoticeListResponse extends BaseResponse{


    /**
     * page : {"currPage":1,"list":[{"mCalories":"1000CAL","mContent":"早餐就要吃美味的牛排","mCreateTime":"2017-08-10 18:16:51","mFavorNum":1,"mId":1,"mImg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502360262&di=839e07b961b88d075eac4d42a8e36a3a&src=http://www.zjjk365.com/UploadFiles/newsPic/201511182.jpeg","mPraiseNum":0,"mScanNum":0,"mSource":"机关食堂","mTitle":"营养早餐","mType":1,"timeType":1}],"pageSize":10,"totalCount":1,"totalPage":1}
     * errCode : 0
     * msg : success
     */

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * currPage : 1
         * list : [{"mCalories":"1000CAL","mContent":"早餐就要吃美味的牛排","mCreateTime":"2017-08-10 18:16:51","mFavorNum":1,"mId":1,"mImg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502360262&di=839e07b961b88d075eac4d42a8e36a3a&src=http://www.zjjk365.com/UploadFiles/newsPic/201511182.jpeg","mPraiseNum":0,"mScanNum":0,"mSource":"机关食堂","mTitle":"营养早餐","mType":1,"timeType":1}]
         * pageSize : 10
         * totalCount : 1
         * totalPage : 1
         */

        private int currPage;
        private int pageSize;
        private int totalCount;
        private int totalPage;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * mCalories : 1000CAL
             * mContent : 早餐就要吃美味的牛排
             * mCreateTime : 2017-08-10 18:16:51
             * mFavorNum : 1
             * mId : 1
             * mImg : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1502360262&di=839e07b961b88d075eac4d42a8e36a3a&src=http://www.zjjk365.com/UploadFiles/newsPic/201511182.jpeg
             * mPraiseNum : 0
             * mScanNum : 0
             * mSource : 机关食堂
             * mTitle : 营养早餐
             * mType : 1
             * timeType : 1
             */

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
            private int timeType;

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

            public int getTimeType() {
                return timeType;
            }

            public void setTimeType(int timeType) {
                this.timeType = timeType;
            }
        }
    }
}
