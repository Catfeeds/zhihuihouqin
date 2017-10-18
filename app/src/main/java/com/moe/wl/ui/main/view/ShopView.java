package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ServiceBean;
import com.moe.wl.ui.main.bean.ShopBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface ShopView extends MvpView{
void getShopInfo(ShopBean shopBean);
void getServiceInfo(ServiceBean bean);


}
