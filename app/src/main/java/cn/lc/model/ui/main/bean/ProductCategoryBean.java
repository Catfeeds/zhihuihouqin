package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class ProductCategoryBean {

    /**
     * page : {"currPage":1,"list":[{"categoryOneId":1,"categoryTwoId":2,"createtime":"2017-08-18 16:17:46","id":2,"imgList":[],"imgs":null,"mainskuid":4,"onsaleTime":"2017-08-19 16:18:28","price":"3.00","pricerange":"5.00-50.00","productImg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503054658127&di=47bddeef1d2cf2f","productname":"订书机","status":1},{"categoryOneId":1,"categoryTwoId":2,"createtime":"2017-08-17 15:13:20","id":1,"imgList":[],"imgs":null,"mainskuid":1,"onsaleTime":"2017-08-16 15:18:38","price":"1.00","pricerange":"1.0-5.99","productImg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","productname":"夹子","status":1}],"pageSize":10,"totalCount":2,"totalPage":1}
     * errCode : 0
     * msg : success
     */

    private PageBean page;
    private int errCode;
    private String msg;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
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

    public static class PageBean {
        /**
         * currPage : 1
         * list : [{"categoryOneId":1,"categoryTwoId":2,"createtime":"2017-08-18 16:17:46","id":2,"imgList":[],"imgs":null,"mainskuid":4,"onsaleTime":"2017-08-19 16:18:28","price":"3.00","pricerange":"5.00-50.00","productImg":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503054658127&di=47bddeef1d2cf2f","productname":"订书机","status":1},{"categoryOneId":1,"categoryTwoId":2,"createtime":"2017-08-17 15:13:20","id":1,"imgList":[],"imgs":null,"mainskuid":1,"onsaleTime":"2017-08-16 15:18:38","price":"1.00","pricerange":"1.0-5.99","productImg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","productname":"夹子","status":1}]
         * pageSize : 10
         * totalCount : 2
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
             * categoryOneId : 1
             * categoryTwoId : 2
             * createtime : 2017-08-18 16:17:46
             * id : 2
             * imgList : []
             * imgs : null
             * mainskuid : 4
             * onsaleTime : 2017-08-19 16:18:28
             * price : 3.00
             * pricerange : 5.00-50.00
             * productImg : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503054658127&di=47bddeef1d2cf2f
             * productname : 订书机
             * status : 1
             */

            private int categoryOneId;
            private int categoryTwoId;
            private String createtime;
            private int id;
            private Object imgs;
            private int mainskuid;
            private String onsaleTime;
            private String price;
            private String pricerange;
            private String productImg;
            private String productname;
            private int status;
            private List<?> imgList;

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

            public List<?> getImgList() {
                return imgList;
            }

            public void setImgList(List<?> imgList) {
                this.imgList = imgList;
            }
        }
    }
}
