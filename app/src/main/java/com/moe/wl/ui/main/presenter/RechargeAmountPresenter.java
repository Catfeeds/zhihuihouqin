package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.RechargeAmountModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.RechargeAmountView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeAmountPresenter extends MvpRxPresenter<RechargeAmountModel, RechargeAmountView> {

    public void rechargeAmount(double money,int paytype,int ordertype) {
        getView().showProgressDialog();
        Observable request = getModel().getData(money,paytype,ordertype);
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
                    getView().rechargeResult(bean);
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
