package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.GetChargeOrderBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.RechargeOrderModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.RechargeOrderView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class RechargeOrderPresenter extends MvpRxPresenter<RechargeOrderModel, RechargeOrderView> {

    public void getOrder(int page,int limit,int type) {
        getView().showProgressDialog();
        Observable request = getModel().getOrder(page,limit,type);
        getNetWork(request, new Subscriber<GetChargeOrderBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable==", e.getMessage());
            }

            @Override
            public void onNext(GetChargeOrderBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getOrder(mResponse);
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
