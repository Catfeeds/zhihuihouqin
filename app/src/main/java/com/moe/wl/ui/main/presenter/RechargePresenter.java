package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.FindChargeOrderBean;
import com.moe.wl.ui.main.model.RechargeModel;
import com.moe.wl.ui.main.view.RechargeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargePresenter extends MvpRxPresenter<RechargeModel, RechargeView> {

    public void getData() {
        getView().showProgressDialog();
        Observable request = getModel().getRechargeData();
        getNetWork(request, new Subscriber<FindChargeOrderBean>() {

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
            public void onNext(FindChargeOrderBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
//                if (bean.getErrCode() == 0) {
//                    getView().rechargeResult(bean);
//                } else {
//                    getView().showToast(bean.getMsg());
//                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
