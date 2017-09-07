package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.Reason;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface CancelOrderView extends MvpView {

    // 获取的原因列表
    void getReasonList(Reason reason);

    // 取消订单
    void cancelOrder(CollectBean bean);

}
