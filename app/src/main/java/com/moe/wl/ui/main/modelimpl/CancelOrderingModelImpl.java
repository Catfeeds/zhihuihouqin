package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.CancelOrderingModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class CancelOrderingModelImpl implements CancelOrderingModel {

    // 获取取消订单原因
    @Override
    public Observable getCancelReason(int type) {
        Observable observer = RetrofitUtils.getInstance().getCancelReason(type);
        return observer;
    }

    // 取消报修订单
    @Override
    public Observable cancelRepairOrder(int oid, String reason) {
        Observable observer = RetrofitUtils.getInstance().cancelRepairOrder(oid, reason);
        return observer;
    }

    // 取消办公用品订单
    @Override
    public Observable cancelOfficeOrder(int oid, int[] reasonIds, String reasonContent) {
        Observable observer = RetrofitUtils.getInstance().cancelOfficeOrder(oid, reasonIds, reasonContent);
        return observer;
    }

    // 取消订餐订单
    @Override
    public Observable cancelMealOrder(int oid, int[] reasonIds, String reasonContent) {
        Observable observer = RetrofitUtils.getInstance().cancelMealOrder(oid, reasonIds, reasonContent);
        return observer;
    }

    // 取消剪发订单
    @Override
    public Observable cancelHaircutsOrder(int oid, String reason) {
        Observable observer = RetrofitUtils.getInstance().cancelHaircutsOrder(oid, reason);
        return observer;
    }

    // 取消订水订单
    @Override
    public Observable cancelWaterOrder(int oid, int[] reasonIds, String reasonContent) {
        Observable observer = RetrofitUtils.getInstance().cancelWaterOrder(oid, reasonIds, reasonContent);
        return observer;
    }

    // 取消医疗订单
    @Override
    public Observable cancelMedicalOrder(int oid, String reason) {
        Observable observer = RetrofitUtils.getInstance().cancelMedicalOrder(oid, reason);
        return observer;
    }

    // 取消专家订单
    @Override
    public Observable cancelExpertsOrder(int oid, String reason) {
        Observable observer = RetrofitUtils.getInstance().cancelExpertsOrder(oid, reason);
        return observer;
    }

    // 取消干洗订单
    @Override
    public Observable cancelDryOrder(int oid, int[] reasonIds, String reasonContent) {
        Observable observer = RetrofitUtils.getInstance().cancelDryCancel(oid, reasonIds, reasonContent);
        return observer;
    }

    // 取消图书订单
    @Override
    public Observable cancelBookOrder(int oid, String reason) {
        Observable observer = RetrofitUtils.getInstance().cancelBookOrder(oid, reason);
        return observer;
    }

    @Override
    public Observable cancelVegetableOrder(int oid, int[] reasonIds, String reasonContent) {
        Observable observer = RetrofitUtils.getInstance().cancelVegetableOrder(oid, reasonIds, reasonContent);
        return observer;
    }
}
