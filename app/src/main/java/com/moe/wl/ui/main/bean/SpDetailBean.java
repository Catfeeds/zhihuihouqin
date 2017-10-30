package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpDetailBean {


    /**
     * product : {"categoryOneId":1,"categoryTwoId":2,"createtime":"2017-08-17 15:13:20","id":1,"imgList":[],"imgs":null,"mainskuid":1,"onsaleTime":"2017-08-16 15:18:38","price":null,"pricerange":"1.0-5.99","productImg":null,"productname":"夹子","status":1,"imgEntityList":[{"id":29,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710271509083707756.png","productId":8,"sort":1},{"id":30,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710271509083716382.jpg","productId":8,"sort":2}]}
     * rateGood : 0
     * commentTotal : 0
     * favorNum : 0
     * errCode : 0
     * productRemain : 160
     * msg : success
     * commentList : [{"anonymous":0,"commenttype":1,"content":"1","createtime":"2017-08-17 17:10:26","id":1,"imgList":["123","456"],"imgs":null,"orderid":1,"photo":"http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg","productid":1,"realname":"小明","score":1,"uid":1}]
     */

    private ProductBean product;
    private int rateGood;
    private int commentTotal;
    private int favorNum;
    private int errCode;
    private int productRemain;
    private String msg;
    private List<CommentListBean> commentList;

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }

    public int getRateGood() {
        return rateGood;
    }

    public void setRateGood(int rateGood) {
        this.rateGood = rateGood;
    }

    public int getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(int commentTotal) {
        this.commentTotal = commentTotal;
    }

    public int getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(int favorNum) {
        this.favorNum = favorNum;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getProductRemain() {
        return productRemain;
    }

    public void setProductRemain(int productRemain) {
        this.productRemain = productRemain;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public static class ProductBean {
        /**
         * categoryOneId : 1
         * categoryTwoId : 2
         * createtime : 2017-08-17 15:13:20
         * id : 1
         * imgList : []
         * imgs : null
         * mainskuid : 1
         * onsaleTime : 2017-08-16 15:18:38
         * price : null
         * pricerange : 1.0-5.99
         * productImg : null
         * productname : 夹子
         * status : 1
         * imgEntityList : [{"id":29,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710271509083707756.png","productId":8,"sort":1},{"id":30,"img":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710271509083716382.jpg","productId":8,"sort":2}]
         */

        private int categoryOneId;
        private int categoryTwoId;
        private String createtime;
        private int id;
        private Object imgs;
        private int mainskuid;
        private String onsaleTime;
        private Object price;
        private String pricerange;
        private Object productImg;
        private String productname;
        private int status;
        private List<?> imgList;
        private List<ImgEntityListBean> imgEntityList;

        public int getCategoryOneId() {
            return categoryOneId;
        }

        public void setCategoryOneId(int categoryOneId) {
            this.categoryOneId = categoryOneId;
        }

        public int getCategoryTwoId() {
            return categoryTwoId;
        }

        public void setCategoryTwoId(int categoryTwoId) {
            this.categoryTwoId = categoryTwoId;
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

        public Object getProductImg() {
            return productImg;
        }

        public void setProductImg(Object productImg) {
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

        public List<?> getImgList() {
            return imgList;
        }

        public void setImgList(List<?> imgList) {
            this.imgList = imgList;
        }

        public List<ImgEntityListBean> getImgEntityList() {
            return imgEntityList;
        }

        public void setImgEntityList(List<ImgEntityListBean> imgEntityList) {
            this.imgEntityList = imgEntityList;
        }

        public static class ImgEntityListBean {
            /**
             * id : 29
             * img : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201710271509083707756.png
             * productId : 8
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

    public static class CommentListBean {
        /**
         * anonymous : 0
         * commenttype : 1
         * content : 1
         * createtime : 2017-08-17 17:10:26
         * id : 1
         * imgList : ["123","456"]
         * imgs : null
         * orderid : 1
         * photo : http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg
         * productid : 1
         * realname : 小明
         * score : 1
         * uid : 1
         */

        private int anonymous;
        private int commenttype;
        private String content;
        private String createtime;
        private int id;
        private Object imgs;
        private int orderid;
        private String photo;
        private int productid;
        private String realname;
        private int score;
        private int uid;
        private List<String> imgList;

        public int getAnonymous() {
            return anonymous;
        }

        public void setAnonymous(int anonymous) {
            this.anonymous = anonymous;
        }

        public int getCommenttype() {
            return commenttype;
        }

        public void setCommenttype(int commenttype) {
            this.commenttype = commenttype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public Object getImgs() {
            return imgs;
        }

        public void setImgs(Object imgs) {
            this.imgs = imgs;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public List<String> getImgList() {
            return imgList;
        }

        public void setImgList(List<String> imgList) {
            this.imgList = imgList;
        }
    }
}
