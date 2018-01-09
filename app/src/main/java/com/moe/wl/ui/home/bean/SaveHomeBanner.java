package com.moe.wl.ui.home.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/16 0016.
 */

public class SaveHomeBanner {

    /**
     * msg : success
     * data : [{"creatTime":"2017-12-16 14:41:00","id":1,"imgUrl":"http://172.24.11.3/zhhq/dcfw/img20171216/1513405829106.jpg","sort":0},{"creatTime":"2017-12-16 14:43:21","id":2,"imgUrl":"http://172.24.11.3/zhhq/dcfw/img20171216/1513406555296.jpg","sort":1}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * creatTime : 2017-12-16 14:41:00
         * id : 1
         * imgUrl : http://172.24.11.3/zhhq/dcfw/img20171216/1513405829106.jpg
         * sort : 0
         */

        private String creatTime;
        private int id;
        private String imgUrl;
        private int sort;

        public String getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(String creatTime) {
            this.creatTime = creatTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
