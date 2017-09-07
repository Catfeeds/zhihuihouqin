package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.NutritionBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public interface RecipeView extends MvpView {

    void getTodayRecipe(NutritionBean bean);

}
