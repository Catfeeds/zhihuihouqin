package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public interface RecipeModel extends MvpModel {

    Observable getTodayRecipe(int timeType, int type);

}
