package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.NutritionBean;
import cn.lc.model.ui.main.model.NutritionModel;
import cn.lc.model.ui.main.view.NutritionView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionPresenter extends MvpRxPresenter<NutritionModel, NutritionView> {

    // 获取套餐
    public void getNutritionData(int type) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable login = getModel().getNutritionData(type);
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
                    getView().getNutritionList(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    // 获取今日菜谱
    /*public void getTodayRecipe(int timeType, int type) {
        getView().showProgressDialog();
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
    }*/

    // 获取Banner
    public void getNutritionBanner() {
        getView().showProgressDialog();
        Observable login = getModel().getNutritionBanner();
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
                    getView().getNutritionBanner();
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
