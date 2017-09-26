package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.NutritionBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public interface NutritionView extends MvpView {

//    void getTodayRecipe(NutritionBean bean);

    void getNutritionList(NutritionBean bean);

    void getNutritionBanner();

}
