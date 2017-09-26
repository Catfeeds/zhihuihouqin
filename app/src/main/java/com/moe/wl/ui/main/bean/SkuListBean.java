package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/23 0023.
 */

public class SkuListBean {
    /**
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
     * store : 46
     */

    private String cataName;
    private String createtime;
    private int firstcataid;
    private int id;
    private String mainimg;
    private int price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
