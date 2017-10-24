package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ClothBean;
import com.moe.wl.ui.main.bean.JieYueBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DryCleanReserveInfoView extends MvpView {

    void OrderDryCleaner(ClothBean cleanBean);

    void commitSucc(JieYueBean jieyueBean);

}
