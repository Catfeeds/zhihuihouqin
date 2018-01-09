
package com.moe.wl.ui.home.view.saving;

import com.moe.wl.ui.home.bean.SaveHomeBanner;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface InformationDetailView extends MvpView {

    void getDetail(InforMationDetailBean bean);


}
