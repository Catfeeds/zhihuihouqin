package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.JieYueBean;
import com.moe.wl.ui.main.bean.OrderDryCleanBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DryCleanReserveInfoView extends MvpView{

void OrderDryCleaner(OrderDryCleanBean cleanBean, boolean getMore);
    void commitSucc(JieYueBean jieyueBean);

}
