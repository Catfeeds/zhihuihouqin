package com.moe.wl.ui.home.bean.office;

import com.moe.wl.framework.base.BaseResponse;

import java.util.List;

/**
 * 办公室预订列表解析
 */

public class OfficeDetailsResponse extends BaseResponse {

    private RoomDetailEntity roomDetail;

    public RoomDetailEntity getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetailEntity roomDetail) {
        this.roomDetail = roomDetail;
    }

    public static class RoomDetailEntity {

        private String address;
        private String area;
        private int capacity;
        private String enames;
        private int id;
        private String imgs;
        private String introduce;
        private String name;
        private String photo;
        private String snames;
        private int status;
        private int usenumber;
        private List<EnameListEntity> enameList;
        private List<Integer> equipmentList;
        private List<ImgEntityListEntity> imgEntityList;
        private List<String> imgList;
        private List<Integer> serviceList;
        private List<SlistEntity> slist;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
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

        public String getSnames() {
            return snames;
        }

        public void setSnames(String snames) {
            this.snames = snames;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUsenumber() {
            return usenumber;
        }

        public void setUsenumber(int usenumber) {
            this.usenumber = usenumber;
        }

        public List<EnameListEntity> getEnameList() {
            return enameList;
        }

        public void setEnameList(List<EnameListEntity> enameList) {
            this.enameList = enameList;
        }

        public List<Integer> getEquipmentList() {
            return equipmentList;
        }

        public void setEquipmentList(List<Integer> equipmentList) {
            this.equipmentList = equipmentList;
        }

        public List<ImgEntityListEntity> getImgEntityList() {
            return imgEntityList;
        }

        public void setImgEntityList(List<ImgEntityListEntity> imgEntityList) {
            this.imgEntityList = imgEntityList;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }

        public List<Integer> getServiceList() {
            return serviceList;
        }

        public void setServiceList(List<Integer> serviceList) {
            this.serviceList = serviceList;
        }

        public List<SlistEntity> getSlist() {
            return slist;
        }

        public void setSlist(List<SlistEntity> slist) {
            this.slist = slist;
        }

        public static class EnameListEntity {

            private int id;
            private String name;
            private int estatus;

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

            public int getEstatus() {
                return estatus;
            }

            public void setEstatus(int estatus) {
                this.estatus = estatus;
            }
        }

        public static class ImgEntityListEntity {

            private int id;
            private String img;
            private int productId;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }

        public static class SlistEntity {

            private int id;
            private String name;
            private int estatus;

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

            public int getEstatus() {
                return estatus;
            }

            public void setEstatus(int estatus) {
                this.estatus = estatus;
            }
        }
    }
}
