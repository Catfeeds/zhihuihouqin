package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/20 0020.
 */

public class ShopCarInfoBean {
    /**
     * msg : success
     * skuList : [{"cataName":null,"catainfolist":[{"cataid":2,"content":"8G","id":4,"productid":52,"skuid":76},{"cataid":1,"content":"red","id":1,"productid":52,"skuid":76}],"createtime":"2017-11-21 19:03:49","failedreason":null,"failedreasonids":null,"firstcataid":2,"gg":null,"id":76,"isMain":0,"mainimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201711211511262217898.jpg","price":1.5,"productid":52,"secondcataid":null,"skuname":"得力胶棒（21G）/支","status":1,"store":199},{"cataName":null,"catainfolist":[{"cataid":1,"content":"yellow","id":8,"productid":52,"skuid":77},{"cataid":2,"content":"8G","id":7,"productid":52,"skuid":77}],"createtime":"2017-11-21 19:04:23","failedreason":null,"failedreasonids":null,"firstcataid":2,"gg":null,"id":77,"isMain":0,"mainimg":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201711211511262217898.jpg","price":3,"productid":52,"secondcataid":null,"skuname":"得力胶棒（36G）/支","status":1,"store":189}]
     * allskuinfolist : [{"cataid":1,"cataname":"颜色分类","infolist":[{"cataid":1,"content":"red","id":null,"productid":52,"skuid":null},{"cataid":1,"content":"yellow","id":null,"productid":52,"skuid":null}]},{"cataid":2,"cataname":"大小分类","infolist":[{"cataid":2,"content":"8G","id":null,"productid":52,"skuid":null}]}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<SkuListBean> skuList;
    private List<AllskuinfolistBean> allskuinfolist;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public List<AllskuinfolistBean> getAllskuinfolist() {
        return allskuinfolist;
    }

    public void setAllskuinfolist(List<AllskuinfolistBean> allskuinfolist) {
        this.allskuinfolist = allskuinfolist;
    }

    public static class SkuListBean implements Serializable{
        /**
         * cataName : null
         * catainfolist : [{"cataid":2,"content":"8G","id":4,"productid":52,"skuid":76},{"cataid":1,"content":"red","id":1,"productid":52,"skuid":76}]
         * createtime : 2017-11-21 19:03:49
         * failedreason : null
         * failedreasonids : null
         * firstcataid : 2
         * gg : null
         * id : 76
         * isMain : 0
         * mainimg : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:dcfw:img201711211511262217898.jpg
         * price : 1.5
         * productid : 52
         * secondcataid : null
         * skuname : 得力胶棒（21G）/支
         * status : 1
         * store : 199
         */

        private String cataName;
        private String createtime;
        private Object failedreason;
        private Object failedreasonids;
        private int firstcataid;
        private Object gg;
        private int id;
        private int isMain;
        private String mainimg;
        private double price;
        private int productid;
        private Object secondcataid;
        private String skuname;
        private int status;
        private int store;
        private List<CatainfolistBean> catainfolist;

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

        public Object getFailedreason() {
            return failedreason;
        }

        public void setFailedreason(Object failedreason) {
            this.failedreason = failedreason;
        }

        public Object getFailedreasonids() {
            return failedreasonids;
        }

        public void setFailedreasonids(Object failedreasonids) {
            this.failedreasonids = failedreasonids;
        }

        public int getFirstcataid() {
            return firstcataid;
        }

        public void setFirstcataid(int firstcataid) {
            this.firstcataid = firstcataid;
        }

        public Object getGg() {
            return gg;
        }

        public void setGg(Object gg) {
            this.gg = gg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsMain() {
            return isMain;
        }

        public void setIsMain(int isMain) {
            this.isMain = isMain;
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

        public List<CatainfolistBean> getCatainfolist() {
            return catainfolist;
        }

        public void setCatainfolist(List<CatainfolistBean> catainfolist) {
            this.catainfolist = catainfolist;
        }

        public static class CatainfolistBean implements Serializable {
            /**
             * cataid : 2
             * content : 8G
             * id : 4
             * productid : 52
             * skuid : 76
             */

            private int cataid;
            private String content;
            private int id;
            private int productid;
            private int skuid;

            public int getCataid() {
                return cataid;
            }

            public void setCataid(int cataid) {
                this.cataid = cataid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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

            public int getSkuid() {
                return skuid;
            }

            public void setSkuid(int skuid) {
                this.skuid = skuid;
            }
        }
    }

    public static class AllskuinfolistBean {
        /**
         * cataid : 1
         * cataname : 颜色分类
         * infolist : [{"cataid":1,"content":"red","id":null,"productid":52,"skuid":null},{"cataid":1,"content":"yellow","id":null,"productid":52,"skuid":null}]
         */

        private int cataid;
        private String cataname;
        private List<InfolistBean> infolist;

        public int getCataid() {
            return cataid;
        }

        public void setCataid(int cataid) {
            this.cataid = cataid;
        }

        public String getCataname() {
            return cataname;
        }

        public void setCataname(String cataname) {
            this.cataname = cataname;
        }

        public List<InfolistBean> getInfolist() {
            return infolist;
        }

        public void setInfolist(List<InfolistBean> infolist) {
            this.infolist = infolist;
        }

        public static class InfolistBean {
            /**
             * cataid : 1
             * content : red
             * id : null
             * productid : 52
             * skuid : null
             */

            private int cataid;
            private String content;
            private Object id;
            private int productid;
            private Object skuid;

            public int getCataid() {
                return cataid;
            }

            public void setCataid(int cataid) {
                this.cataid = cataid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public int getProductid() {
                return productid;
            }

            public void setProductid(int productid) {
                this.productid = productid;
            }

            public Object getSkuid() {
                return skuid;
            }

            public void setSkuid(Object skuid) {
                this.skuid = skuid;
            }
        }
    }
/*
    *//**
     * errCode : 0
     * skuList : [{"cataName":"颜色分类","createtime":"2017-08-18 16:23:41","firstcataid":1,"id":1,"mainimg":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=15030","price":1,"productid":1,"secondcataid":null,"skuname":"小尺寸多用夹(蓝色)","status":1,"store":46}]
     * msg : success
     *//*

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
      *//*  *
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
         * store : 46*//*


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
    }*/


}
