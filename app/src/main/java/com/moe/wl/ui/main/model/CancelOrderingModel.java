package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface CancelOrderingModel extends MvpModel {

    // 获取取消订单原因
    Observable getCancelReason(int type);

    // 取消报修订单
    Observable cancelRepairOrder(int oid, String reason);

    // 取消办公用品订单
    Observable cancelOfficeOrder(int oid, int[] reasonIds, String reasonContent);

    // 取消订餐订单
    Observable cancelMealOrder(int oid, int[] reasonIds, String reasonContent);

    // 取消理发订单
    Observable cancelHaircutsOrder(int oid, String reason);

    // 取消订水订单
    Observable cancelWaterOrder(int oid, int[] reasonIds, String reasonContent);

    // 取消医疗订单
    Observable cancelMedicalOrder(int oid, String reason);

    // 取消专家坐诊订单
    Observable cancelExpertsOrder(int oid, String reason);

    // 取消干洗订单
    Observable cancelDryOrder(int oid, int[] reasonIds, String reasonContent);

    // 取消图书订单
    Observable cancelBookOrder(int oid, String reason);

    // 取消净菜订单
    Observable cancelVegetableOrder(int oid, int[] reasonIds, String reasonContent);

}
