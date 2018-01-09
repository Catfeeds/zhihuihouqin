
package com.moe.wl.ui.home.view.saving;

import com.moe.wl.ui.home.bean.saving.EnergyCostCompare;
import com.moe.wl.ui.main.bean.BuildNumList;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ComparisonView extends MvpView {

    void setData();
    void getBuildNum(BuildNumList bean);
    void getInfo(EnergyCostCompare bean);

}
