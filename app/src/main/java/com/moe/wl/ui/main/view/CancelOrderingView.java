package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ReasonBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface CancelOrderingView extends MvpView {

    // 获取的原因列表
    void getReasonList(ReasonBean reasonBean);

    // 取消订单
    void cancelOrder(CollectBean bean);

}
