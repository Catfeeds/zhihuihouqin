package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.UserDepositBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MyDepositModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.MyDepositView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MyDepositPresenter extends MvpRxPresenter<MyDepositModel, MyDepositView> {

    public void getDepositInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getDepositInfo();
        getNetWork(request, new Subscriber<UserDepositBean>() {

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
            public void onNext(UserDepositBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getUserDepositResult(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void generateChargeWalletOrder(double s,int s1,int s2) {
        getView().showProgressDialog();
        Observable request = getModel().generateChargeWalletOrder(s,s1,s2);
        getNetWork(request, new Subscriber<WalletOrderBean>() {

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
            public void onNext(WalletOrderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getOrderResult(bean);
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
