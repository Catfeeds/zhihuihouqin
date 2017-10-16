package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.PayModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.PayView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PayPresenter extends MvpRxPresenter<PayModel, PayView> {

    public void aliPay(String orderid,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordertype,paytype);
        getNetWork(request, new Subscriber<AlipayBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(AlipayBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().aliPay(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void weiXinPay(String orderid,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordertype,paytype);
        getNetWork(request, new Subscriber<WeixinBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(WeixinBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().weiXinPay(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void personalWalletPay(String orderid,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordertype,paytype);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().personalWallet(bean);
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
