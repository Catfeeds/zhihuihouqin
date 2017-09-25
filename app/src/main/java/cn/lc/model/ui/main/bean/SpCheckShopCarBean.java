package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/21 0021.
 */

public class SpCheckShopCarBean {

    /**
     * cartItems : [{"count":2,"createtime":"2017-08-22 16:09:57","id":1,"productid":1,"sku":{"cataName":"颜色分类","createtime":null,"firstcataid":null,"id":1,"mainimg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","price":1,"productid":null,"secondcataid":null,"skuname":"小尺寸多用夹(蓝色)","status":null,"store":null},"skuid":1,"uid":1}]
     * errCode : 0
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<CartItemsBean> cartItems;

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

    public List<CartItemsBean> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsBean> cartItems) {
        this.cartItems = cartItems;
    }

    public static class CartItemsBean {
        /**
         * count : 2
         * createtime : 2017-08-22 16:09:57
         * id : 1
         * productid : 1
         * sku : {"cataName":"颜色分类","createtime":null,"firstcataid":null,"id":1,"mainimg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","price":1,"productid":null,"secondcataid":null,"skuname":"小尺寸多用夹(蓝色)","status":null,"store":null}
         * skuid : 1
         * uid : 1
         */

        private int count;
        private String createtime;
        private int id;
        private int productid;
        private SkuBean sku;
        private int skuid;
        private int uid;
        private boolean isSeclect;

        public boolean isSeclect() {
            return isSeclect;
        }

        public void setSeclect(boolean seclect) {
            isSeclect = seclect;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
            this.productid = productid;
        }

        public SkuBean getSku() {
            return sku;
        }

        public void setSku(SkuBean sku) {
            this.sku = sku;
        }

        public int getSkuid() {
            return skuid;
        }

        public void setSkuid(int skuid) {
            this.skuid = skuid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public static class SkuBean {
            /**
             * cataName : 颜色分类
             * createtime : null
             * firstcataid : null
             * id : 1
             * mainimg : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030
             * price : 1
             * productid : null
             * secondcataid : null
             * skuname : 小尺寸多用夹(蓝色)
             * status : null
             * store : null
             */

            private String cataName;
            private Object createtime;
            private Object firstcataid;
            private int id;
            private String mainimg;
            private int price;
            private Object productid;
            private Object secondcataid;
            private String skuname;
            private Object status;
            private int store;

            public String getCataName() {
                return cataName;
            }

            public void setCataName(String cataName) {
                this.cataName = cataName;
            }

            public Object getCreatetime() {
                return createtime;
            }

            public void setCreatetime(Object createtime) {
                this.createtime = createtime;
            }

            public Object getFirstcataid() {
                return firstcataid;
            }

            public void setFirstcataid(Object firstcataid) {
                this.firstcataid = firstcataid;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMainimg() {
                return mainimg;
            }

            public void setMainimg(String mainimg) {
                this.mainimg = mainimg;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public Object getProductid() {
                return productid;
            }

            public void setProductid(Object productid) {
                this.productid = productid;
            }

            public Object getSecondcataid() {
                return secondcataid;
            }

            public void setSecondcataid(Object secondcataid) {
                this.secondcataid = secondcataid;
            }

            public String getSkuname() {
                return skuname;
            }

            public void setSkuname(String skuname) {
                this.skuname = skuname;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public int getStore() {
                return store;
            }

            public void setStore(int store) {
                this.store = store;
            }
        }
    }
}
