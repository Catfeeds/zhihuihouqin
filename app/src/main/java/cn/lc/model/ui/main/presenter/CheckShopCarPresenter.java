package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.bean.SpCheckShopCarBean;
import cn.lc.model.ui.main.model.CheckShopCarModel;
import cn.lc.model.ui.main.view.CheckShopCarView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class CheckShopCarPresenter extends MvpRxPresenter<CheckShopCarModel, CheckShopCarView> {

    public void checkShopCar() {
        getView().showProgressDialog();
        Observable request = getModel().checkShopCar();
        getNetWork(request, new Subscriber<SpCheckShopCarBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(SpCheckShopCarBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().checkShopCar(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void cancelItem(int[] arr) {
        getView().showProgressDialog();
        Observable request = getModel().cancelItem(arr);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().cancelSucc(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
