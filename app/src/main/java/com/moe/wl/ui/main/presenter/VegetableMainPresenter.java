package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CanOrderedBean;
import com.moe.wl.ui.main.bean.VegetableBean;
import com.moe.wl.ui.main.model.VegetableMainModel;
import com.moe.wl.ui.main.view.VegetableMainView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class VegetableMainPresenter extends MvpRxPresenter<VegetableMainModel, VegetableMainView> {

    public void getVegetableData(int page, String keyword) {
        getView().showProgressDialog();
        Observable request = getModel().getVegetableData(page, keyword);
        getNetWork(request, new Subscriber<VegetableBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
                getView().onError();
            }

            @Override
            public void onNext(VegetableBean mResponse) {
                getView().onError();
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getVegetableDataSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    public void canOrdered() {
        getView().showProgressDialog();
        Observable request = getModel().canOrdered();
        getNetWork(request, new Subscriber<CanOrderedBean>() {

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
            public void onNext(CanOrderedBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().canOrderedResult(mResponse);
                } else if (mResponse.getErrCode() == 1001) {
                    getView().canOrderedResult(mResponse);
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
