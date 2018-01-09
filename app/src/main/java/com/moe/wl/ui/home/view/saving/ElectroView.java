
package com.moe.wl.ui.home.view.saving;

import com.moe.wl.ui.home.bean.EnergyCostQueryBean;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ElectroView extends MvpView {

    void getInfo(EnergyCostQueryBean bean);


}
