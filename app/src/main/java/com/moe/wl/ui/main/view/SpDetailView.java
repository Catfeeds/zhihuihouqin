package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;
import com.moe.wl.ui.main.bean.SpDetailBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface SpDetailView extends MvpView {

    void getSpDetailSucc(SpDetailBean bean);
    void getCollectResult(CollectBean bean);
    void getShopCarInfo(ShopCarInfoBean bean);
    void getShopCar(ActivityPostBean bean);

}
