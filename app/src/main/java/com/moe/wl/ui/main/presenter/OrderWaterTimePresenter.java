package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.OrderWaterTimeModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.OrderWaterTimeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderWaterTimePresenter extends MvpRxPresenter<OrderWaterTimeModel, OrderWaterTimeView> {

    public void getTime() {
        getView().showProgressDialog();
        Observable request = getModel().getOrderTime();
        getNetWork(request, new Subscriber<OrderWaterTimeBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(OrderWaterTimeBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getTimeSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
