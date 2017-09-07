package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.NutritionModel;
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
