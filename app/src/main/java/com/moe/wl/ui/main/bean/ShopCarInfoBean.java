package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/20 0020.
 */

public class ShopCarInfoBean {

    /**
     * errCode : 0
     * skuList : [{"cataName":"颜色分类","createtime":"2017-08-18 16:23:41","firstcataid":1,"id":1,"mainimg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","price":1,"productid":1,"secondcataid":null,"skuname":"小尺寸多用夹(蓝色)","status":1,"store":46}]
     * msg : success
     */

    private int errCode;
    private String msg;
    private List<SkuListBean> skuList;

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

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public static class SkuListBean implements Serializable{
      /*  *
         * cataName : 颜色分类
         * createtime : 2017-08-18 16:23:41
         * firstcataid : 1
         * id : 1
         * mainimg : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030
         * price : 1
         * productid : 1
         * secondcataid : null
         * skuname : 小尺寸多用夹(蓝色)
         * status : 1
         * store : 46*/


        private String cataName;
        private String createtime;
        private int firstcataid;
        private int id;
        private String mainimg;
        private double price;
        private int productid;
        private Object secondcataid;
        private String skuname;
        private int status;
        private int store;

        public String getCataName() {
            return cataName;
        }

        public void setCataName(String cataName) {
            this.cataName = cataName;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getFirstcataid() {
            return firstcataid;
        }

        public void setFirstcataid(int firstcataid) {
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductid() {
            return productid;
        }

        public void setProductid(int productid) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
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
