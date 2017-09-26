package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.NutritionBean;
import com.moe.wl.ui.main.model.RecipeModel;
import com.moe.wl.ui.main.view.RecipeView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class RecipePresenter extends MvpRxPresenter<RecipeModel, RecipeView> {

    // 获取今日菜谱
    public void getTodayRecipe(int timeType, int type) {
//        getView().showProgressDialog();
        Observable login = getModel().getTodayRecipe(timeType, type);
        getNetWork(login, new Subscriber<NutritionBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(NutritionBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getTodayRecipe(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
