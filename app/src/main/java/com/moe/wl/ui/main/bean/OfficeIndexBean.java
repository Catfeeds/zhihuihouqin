package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class OfficeIndexBean {

    /**
     * categoryList : [{"cgrade":1,"cname":"办公用品","id":1,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709061504694532410.png","pid":0,"pname":null}]
     * topphoto : http://img01.taopic.com/151015/240476-1510150HP187.jpg
     * errCode : 0
     * newProductList : [{"categoryOneId":1,"categoryOneName":"办公用品","categoryTwoId":3,"categoryTwoName":"桌上用品","createtime":"2017-08-18 16:17:46","favorNum":0,"id":2,"imgEntityList":[{"id":6,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855199806.png","productId":2,"sort":3},{"id":4,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855187109.png","productId":2,"sort":1},{"id":5,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855191576.png","productId":2,"sort":4}],"imgList":[],"imgs":null,"mainskuid":8,"onsaleTime":"2017-08-19 16:18:28","price":"10.00","pricerange":"￥10-12","productCode":"DL100101","productImg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504839246520.png","productname":"订书机","status":1,"supplierInfo":null,"supplierName":"得力"}]
     * msg : success
     */

    private String topphoto;
    private int errCode;
    private String msg;
    private List<CategoryListBean> categoryList;
    private List<NewProductListBean> newProductList;

    public String getTopphoto() {
        return topphoto;
    }

    public void setTopphoto(String topphoto) {
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

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public List<NewProductListBean> getNewProductList() {
        return newProductList;
    }

    public void setNewProductList(List<NewProductListBean> newProductList) {
        this.newProductList = newProductList;
    }

    public static class CategoryListBean {
        /**
         * cgrade : 1
         * cname : 办公用品
         * id : 1
         * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709061504694532410.png
         * pid : 0
         * pname : null
         */

        private int cgrade;
        private String cname;
        private int id;
        private String img;
        private int pid;
        private Object pname;

        public int getCgrade() {
            return cgrade;
        }

        public void setCgrade(int cgrade) {
            this.cgrade = cgrade;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public Object getPname() {
            return pname;
        }

        public void setPname(Object pname) {
            this.pname = pname;
        }
    }

    public static class NewProductListBean {
        /**
         * categoryOneId : 1
         * categoryOneName : 办公用品
         * categoryTwoId : 3
         * categoryTwoName : 桌上用品
         * createtime : 2017-08-18 16:17:46
         * favorNum : 0
         * id : 2
         * imgEntityList : [{"id":6,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855199806.png","productId":2,"sort":3},{"id":4,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855187109.png","productId":2,"sort":1},{"id":5,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855191576.png","productId":2,"sort":4}]
         * imgList : []
         * imgs : null
         * mainskuid : 8
         * onsaleTime : 2017-08-19 16:18:28
         * price : 10.00
         * pricerange : ￥10-12
         * productCode : DL100101
         * productImg : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504839246520.png
         * productname : 订书机
         * status : 1
         * supplierInfo : null
         * supplierName : 得力
         */

        private int categoryOneId;
        private String categoryOneName;
        private int categoryTwoId;
        private String categoryTwoName;
        private String createtime;
        private int favorNum;
        private int id;
        private Object imgs;
        private int mainskuid;
        private String onsaleTime;
        private String price;
        private String pricerange;
        private String productCode;
        private String productImg;
        private String productname;
        private int status;
        private Object supplierInfo;
        private String supplierName;
        private List<ImgEntityListBean> imgEntityList;
        private List<?> imgList;

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

        public int getCategoryTwoId() {
            return categoryTwoId;
        }

        public void setCategoryTwoId(int categoryTwoId) {
            this.categoryTwoId = categoryTwoId;
        }

        public String getCategoryTwoName() {
            return categoryTwoName;
        }

        public void setCategoryTwoName(String categoryTwoName) {
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

        public String getOnsaleTime() {
            return onsaleTime;
        }

        public void setOnsaleTime(String onsaleTime) {
            this.onsaleTime = onsaleTime;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
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
             * id : 6
             * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201709081504855199806.png
             * productId : 2
             * sort : 3
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
