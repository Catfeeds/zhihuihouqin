package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.PayBean;
import com.moe.wl.ui.main.model.ConfirmVegetableOrderModel;
import com.moe.wl.ui.main.view.ConfirmVegetableOrderView;

import java.util.HashMap;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class ConfirmVegetableOrderPresenter extends MvpRxPresenter<ConfirmVegetableOrderModel, ConfirmVegetableOrderView> {

    public void ConfirmVegetableOrder(HashMap<String, Object> map) {
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
            public void onNext(PayBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().submitVegetableOrderSucc(mResponse);
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
