package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class OrderWaterSumAndcount {
    private int count;
    private int sum;

    public OrderWaterSumAndcount(int count, int sum) {
        this.count = count;
        this.sum = sum;
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
