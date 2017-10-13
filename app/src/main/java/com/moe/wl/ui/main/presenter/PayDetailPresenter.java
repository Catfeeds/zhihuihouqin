package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.FindWalletLogBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.PayDetailModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.PayDetailView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PayDetailPresenter extends MvpRxPresenter<PayDetailModel, PayDetailView> {

    public void getPayDetailInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getData();
        getNetWork(request, new Subscriber<FindWalletLogBean>() {

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
            public void onNext(FindWalletLogBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getPayDetailResult(bean);
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
