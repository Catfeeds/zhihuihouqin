package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.NutritionModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionModelImpl implements NutritionModel {

//    @Override
//    public Observable getTodayRecipe(int timeType, int type) {
//        Observable observable = RetrofitUtils.getInstance().getTodayRecipe(timeType, type);
//        return observable;
//    }

    @Override
    public Observable getNutritionData(int type) {
        Observable observable = RetrofitUtils.getInstance().getNutritionData(type);
        return observable;
    }

    @Override
    public Observable getNutritionBanner() {
        Observable observable = RetrofitUtils.getInstance().getNutritionBanner();
        return observable;
    }

}
