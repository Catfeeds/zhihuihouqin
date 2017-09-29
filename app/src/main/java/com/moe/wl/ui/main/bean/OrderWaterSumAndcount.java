package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class OrderWaterSumAndcount {
    private int count;
    private int sum;
    private QueryWaterListBean.PageBean.ListBean data;
    private List<QueryWaterListBean.PageBean.ListBean> list;
    private int id;
    private boolean isAdd;

    public List<QueryWaterListBean.PageBean.ListBean> getList() {
        return list;
    }

    public void setList(List<QueryWaterListBean.PageBean.ListBean> list) {
        this.list = list;
    }

    public OrderWaterSumAndcount(int count, int sum, QueryWaterListBean.PageBean.ListBean data) {
        this.count = count;
        this.sum = sum;
        this.data = data;
    }

    public OrderWaterSumAndcount(int id, QueryWaterListBean.PageBean.ListBean data, boolean isAdd) {
        this.id = id;
        this.data = data;
        this.isAdd = isAdd;
    }

    public OrderWaterSumAndcount(int count, int sum, List<QueryWaterListBean.PageBean.ListBean> list) {
        this.count = count;
        this.sum = sum;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public QueryWaterListBean.PageBean.ListBean getData() {
        return data;
    }

    public void setData(QueryWaterListBean.PageBean.ListBean data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
