package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.BarberProductDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface HairStyleDetailView extends MvpView {

    void getDataSucc(BarberProductDetailBean bean);

    void collectSucc(CollectBean bean);
}
