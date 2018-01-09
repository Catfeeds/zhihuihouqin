
package com.moe.wl.ui.home.view.saving;

import com.moe.wl.ui.home.bean.SaveHomeBanner;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface InformationView extends MvpView {

    void setData();
    //void getBanner(SaveHomeBanner bean);
    void getHomeList(SaveHomeListBean bean);

}
