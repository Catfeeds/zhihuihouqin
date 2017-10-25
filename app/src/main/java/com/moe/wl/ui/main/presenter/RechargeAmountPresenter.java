package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.RechargeAmountModel;
import com.moe.wl.ui.main.view.RechargeAmountView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeAmountPresenter extends MvpRxPresenter<RechargeAmountModel, RechargeAmountView> {

    public void rechargeAmount(double money,int ordertype) {
        getView().showProgressDialog();
        Observable request = getModel().getData(money,ordertype);
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
            public void onNext(WalletOrderBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().rechargeResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void aliPay(String orderid,String ordercode,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordercode,ordertype,paytype);
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
            public void onNext(AlipayBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().aliPay(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void weiXinPay(String orderid,String ordercode,String ordertype,int paytype) {
        LogUtils.d("");
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordercode,ordertype,paytype);
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
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
