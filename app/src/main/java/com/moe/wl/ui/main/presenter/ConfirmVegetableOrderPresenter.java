package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.view.ConfirmVegetableOrderView;

import java.util.HashMap;

import com.moe.wl.ui.main.bean.PayBean;
import com.moe.wl.ui.main.model.ConfirmVegetableOrderModel;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class ConfirmVegetableOrderPresenter extends MvpRxPresenter<ConfirmVegetableOrderModel, ConfirmVegetableOrderView> {

    public void ConfirmVegetableOrder(HashMap<String, String> map) {
        getView().showProgressDialog();
        Log.e("BookDetailPresenter", "发出请求");
        Observable request = getModel().submitVegetableOrder(map);
        getNetWork(request, new Subscriber<PayBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(PayBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().submitVegetableOrderSucc(bean);
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
