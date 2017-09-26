package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.RecipeModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class RecipeModelImpl implements RecipeModel {

    @Override
    public Observable getTodayRecipe(int timeType, int type) {
        Observable observable = RetrofitUtils.getInstance().getTodayRecipe(timeType, type);
        return observable;
    }
}
