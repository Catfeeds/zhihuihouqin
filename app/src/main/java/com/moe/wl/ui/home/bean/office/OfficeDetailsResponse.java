package com.moe.wl.ui.home.bean.office;

import com.moe.wl.framework.base.BaseResponse;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class OfficeDetailsResponse extends BaseResponse {


    /**
     * roomDetail : {"address":"大楼东侧","capacity":80,"enameList":[{"id":"1","name":"鲜花"},{"id":"2","name":"投影仪"}],"enames":"1-鲜花,2-投影仪,3-热毛巾,4-音响","id":1,"imgList":["11"],"imgs":"11","Stringroduce":"测试测试","name":"会议室2","photo":"1111","status":null,"timeserving":1,"usenumber":20}
     * errCode : 0
     * msg : success
     */

    private RoomDetailBean roomDetail;

    public RoomDetailBean getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetailBean roomDetail) {
        this.roomDetail = roomDetail;
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
        private String capacity;
        private String enames;
        private String id;
        private String imgs;
        private String introduce;
        private String name;
        private String photo;
        private String status;
        private String timeserving;
        private String usenumber;
        private List<EnameListBean> enameList;
        private List<String> imgList;

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

        public String getEnames() {
            return enames;
        }

        public void setEnames(String enames) {
            this.enames = enames;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
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
            private String estatus;

            public String getEstatus() {
                return estatus;
            }

            public void setEstatus(String estatus) {
                this.estatus = estatus;
            }

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
