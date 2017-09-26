package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.VegetableBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */

public interface VegetableMainView extends MvpView {

    void getVegetableDataSucc(VegetableBean bean);

}
