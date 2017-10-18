package com.moe.wl.ui.home.bean.office;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class OfficeListResponse {

    /**
     * topphoto : null
     * errCode : 0
     * list : [{"address":"大楼西侧","capacity":80,"enameList":[],"enames":null,"id":2,"imgList":null,"imgs":"1111","Stringroduce":"测试测试","name":"会议室1","photo":"1111","status":null,"timeserving":1,"usenumber":20}]
     * msg : success
     */

    private Object topphoto;
    private int errCode;
    private String msg;
    private List<ListBean> list;

    public Object getTopphoto() {
        return topphoto;
    }

    public void setTopphoto(Object topphoto) {
        this.topphoto = topphoto;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * address : 大楼西侧
         * capacity : 80
         * enameList : []
         * enames : null
         * id : 2
         * imgList : null
         * imgs : 1111
         * Stringroduce : 测试测试
         * name : 会议室1
         * photo : 1111
         * status : null
         * timeserving : 1
         * usenumber : 20
         */

        private String address;
        private String capacity;
        private Object enames;
        private String id;
        private Object imgList;
        private String imgs;
        private String Stringroduce;
        private String name;
        private String photo;
        private Object status;
        private String timeserving;
        private String usenumber;
        private List<?> enameList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public Object getEnames() {
            return enames;
        }

        public void setEnames(Object enames) {
            this.enames = enames;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getImgList() {
            return imgList;
        }

        public void setImgList(Object imgList) {
            this.imgList = imgList;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getStringroduce() {
            return Stringroduce;
        }

        public void setStringroduce(String Stringroduce) {
            this.Stringroduce = Stringroduce;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getTimeserving() {
            return timeserving;
        }

        public void setTimeserving(String timeserving) {
            this.timeserving = timeserving;
        }

        public String getUsenumber() {
            return usenumber;
        }

        public void setUsenumber(String usenumber) {
            this.usenumber = usenumber;
        }

        public List<?> getEnameList() {
            return enameList;
        }

        public void setEnameList(List<?> enameList) {
            this.enameList = enameList;
        }
    }
}
