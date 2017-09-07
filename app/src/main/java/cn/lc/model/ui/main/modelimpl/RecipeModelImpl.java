package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.RecipeModel;
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
