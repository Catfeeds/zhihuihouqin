package com.moe.wl.ui.home.bean.office;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class OfficeDetailsResponse {


    /**
     * roomDetail : {"address":"大楼东侧","capacity":80,"enameList":[{"id":"1","name":"鲜花"},{"id":"2","name":"投影仪"}],"enames":"1-鲜花,2-投影仪,3-热毛巾,4-音响","id":1,"imgList":["11"],"imgs":"11","introduce":"测试测试","name":"会议室2","photo":"1111","status":null,"timeserving":1,"usenumber":20}
     * errCode : 0
     * msg : success
     */

    private RoomDetailBean roomDetail;
    private int errCode;
    private String msg;

    public RoomDetailBean getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetailBean roomDetail) {
        this.roomDetail = roomDetail;
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

    public static class RoomDetailBean {
        /**
         * address : 大楼东侧
         * capacity : 80
         * enameList : [{"id":"1","name":"鲜花"},{"id":"2","name":"投影仪"}]
         * enames : 1-鲜花,2-投影仪,3-热毛巾,4-音响
         * id : 1
         * imgList : ["11"]
         * imgs : 11
         * introduce : 测试测试
         * name : 会议室2
         * photo : 1111
         * status : null
         * timeserving : 1
         * usenumber : 20
         */

        private String address;
        private int capacity;
        private String enames;
        private int id;
        private String imgs;
        private String introduce;
        private String name;
        private String photo;
        private Object status;
        private int timeserving;
        private int usenumber;
        private List<EnameListBean> enameList;
        private List<String> imgList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public String getEnames() {
            return enames;
        }

        public void setEnames(String enames) {
            this.enames = enames;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
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

        public int getTimeserving() {
            return timeserving;
        }

        public void setTimeserving(int timeserving) {
            this.timeserving = timeserving;
        }

        public int getUsenumber() {
            return usenumber;
        }

        public void setUsenumber(int usenumber) {
            this.usenumber = usenumber;
        }

        public List<EnameListBean> getEnameList() {
            return enameList;
        }

        public void setEnameList(List<EnameListBean> enameList) {
            this.enameList = enameList;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public static class EnameListBean {
            /**
             * id : 1
             * name : 鲜花
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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
}
