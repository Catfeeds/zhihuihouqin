package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface CancelOrderingModel extends MvpModel {

    // 获取取消订单原因
    Observable getReasonList();
    //获取干洗取消原因
    Observable getDryCancelList();
    // 取消订单
    Observable cancelOrder(int oid, int[] reasonIds, String reasonContent);
    // 取消干洗订单
    Observable cancelDryOrder(int oid, String reasonIds, String reasonContent);

}
