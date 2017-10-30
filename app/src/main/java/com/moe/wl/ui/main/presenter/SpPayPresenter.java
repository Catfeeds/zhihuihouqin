package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.UserWalletBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.SpPayModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.SpPayView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpPayPresenter extends MvpRxPresenter<SpPayModel, SpPayView> {

    public void getData(String type) {
        getView().showProgressDialog();
        Observable request = getModel().getData(type);
        getNetWork(request, new Subscriber<UserWalletBean>() {

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
            public void onNext(UserWalletBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    //钱包支付
    public void personalWalletPay(String orderid,String ordercode,String ordertype,int paytype,String paypass,int count) {
        getView().showProgressDialog();
        Observable request = getModel().walletpay(orderid,ordercode,ordertype,paytype,paypass,count);
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
    //是否有支付密码
    public void getIsHasPwd() {
        getView().showProgressDialog();
        Observable request = getModel().getData();
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
            public void onNext(ActivityPostBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().isHasPwd(mResponse);
                } else if(mResponse.getErrCode()==1){
                    getView().isHasPwd(mResponse);
                }
            }
        });
    }
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
