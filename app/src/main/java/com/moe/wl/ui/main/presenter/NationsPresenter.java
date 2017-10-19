package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.NationslistBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.NationsModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.NationsView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class NationsPresenter extends MvpRxPresenter<NationsModel, NationsView> {

    public void getNationList() {
        getView().showProgressDialog();
        Observable request = getModel().getNationList();
        getNetWork(request, new Subscriber<NationslistBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(NationslistBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getNationResult(bean);
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
