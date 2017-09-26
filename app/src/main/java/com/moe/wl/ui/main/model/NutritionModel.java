package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public interface NutritionModel extends MvpModel {

    // 获取今日菜谱
//    Observable getTodayRecipe(int timeType, int type);

    // 获取营养套餐
    Observable getNutritionData(int type);

    // 获取轮播图
    Observable getNutritionBanner();

}
