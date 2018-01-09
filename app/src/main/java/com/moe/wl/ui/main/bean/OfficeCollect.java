package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 作者 Wang
 * 日期 2017/10/25.
 * 描述
 */

public class OfficeCollect {

    /**
     * errCode : 0
     * list : [{"categoryOneId":8,"categoryOneName":"办公纸笔","categoryTwoId":null,"categoryTwoName":null,"createtime":"2017-09-14 14:10:46","favorNum":2,"id":4,"imgEntityList":[{"id":12,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709141505369445392.png","productId":4,"sort":1}],"imgList":[],"imgs":null,"mainskuid":0,"onsaleTime":null,"price":null,"pricerange":"￥2-4","productCode":"FDASA4564","productImg":null,"productname":"钢笔","status":1,"supplierInfo":null,"supplierName":"哈哈哈"}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<ListBean> list;

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
         * categoryOneId : 8
         * categoryOneName : 办公纸笔
         * categoryTwoId : null
         * categoryTwoName : null
         * createtime : 2017-09-14 14:10:46
         * favorNum : 2
         * id : 4
         * imgEntityList : [{"id":12,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709141505369445392.png","productId":4,"sort":1}]
         * imgList : []
         * imgs : null
         * mainskuid : 0
         * onsaleTime : null
         * price : null
         * pricerange : ￥2-4
         * productCode : FDASA4564
         * productImg : null
         * productname : 钢笔
         * status : 1
         * supplierInfo : null
         * supplierName : 哈哈哈
         */

        private int categoryOneId;
        private String categoryOneName;
        private Object categoryTwoId;
        private Object categoryTwoName;
        private String createtime;
        private int favorNum;
        private int id;
        private Object imgs;
        private int mainskuid;
        private Object onsaleTime;
        private Object price;
        private String pricerange;
        private String productCode;
        private String productImg;
        private String productname;
        private int status;
        private Object supplierInfo;
        private String supplierName;
        private List<ImgEntityListBean> imgEntityList;
        private List<?> imgList;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getCategoryOneId() {
            return categoryOneId;
        }

        public void setCategoryOneId(int categoryOneId) {
            this.categoryOneId = categoryOneId;
        }

        public String getCategoryOneName() {
            return categoryOneName;
        }

        public void setCategoryOneName(String categoryOneName) {
            this.categoryOneName = categoryOneName;
        }

        public Object getCategoryTwoId() {
            return categoryTwoId;
        }

        public void setCategoryTwoId(Object categoryTwoId) {
            this.categoryTwoId = categoryTwoId;
        }

        public Object getCategoryTwoName() {
            return categoryTwoName;
        }

        public void setCategoryTwoName(Object categoryTwoName) {
            this.categoryTwoName = categoryTwoName;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getFavorNum() {
            return favorNum;
        }

        public void setFavorNum(int favorNum) {
            this.favorNum = favorNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getImgs() {
            return imgs;
        }

        public void setImgs(Object imgs) {
            this.imgs = imgs;
        }

        public int getMainskuid() {
            return mainskuid;
        }

        public void setMainskuid(int mainskuid) {
            this.mainskuid = mainskuid;
        }

        public Object getOnsaleTime() {
            return onsaleTime;
        }

        public void setOnsaleTime(Object onsaleTime) {
            this.onsaleTime = onsaleTime;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public String getPricerange() {
            return pricerange;
        }

        public void setPricerange(String pricerange) {
            this.pricerange = pricerange;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getProductname() {
            return productname;
        }

        public void setProductname(String productname) {
            this.productname = productname;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getSupplierInfo() {
            return supplierInfo;
        }

        public void setSupplierInfo(Object supplierInfo) {
            this.supplierInfo = supplierInfo;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public List<ImgEntityListBean> getImgEntityList() {
            return imgEntityList;
        }

        public void setImgEntityList(List<ImgEntityListBean> imgEntityList) {
            this.imgEntityList = imgEntityList;
        }

        public List<?> getImgList() {
            return imgList;
        }

        public void setImgList(List<?> imgList) {
            this.imgList = imgList;
        }

        public static class ImgEntityListBean {
            /**
             * id : 12
             * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709141505369445392.png
             * productId : 4
             * sort : 1
             */

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
    }
}
